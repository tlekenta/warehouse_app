package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.StageToWarehouseIdMap;
import pl.edu.wat.warehouse_app.stage.model.Stage_Dostawa;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_F_Dostawa;
import pl.edu.wat.warehouse_app.stage.repository.StageToWarehouseIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.Stage_DostawaRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_F_DostawaRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_DataRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_ProductRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_SklepRepository;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;
import pl.edu.wat.warehouse_app.util.helpers.WarehouseIdsGetter;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DostawaFactTransformer {


    ReflectionUtils reflectionUtils;

    Stage_DostawaRepository stage_dostawaRepository;
    TMP_W_DataRepository tmp_w_dataRepository;
    TMP_W_ProductRepository tmp_w_productRepository;
    TMP_W_SklepRepository tmp_w_sklepRepository;
    TMP_F_DostawaRepository tmp_f_dostawaRepository;

    StageToWarehouseIdMapRepository stageToWarehouseIdMapRepository;

    DbLogger logger;
    WarehouseIdsGetter warehouseIdsGetter;

    public void transform() throws IllegalAccessException {

        List<Stage_Dostawa> sourceSupplies = stage_dostawaRepository.findAll();


        Timestamp lastImport = logger.getLastImportTimestamp(TMP_F_Dostawa.class.getSimpleName());

        //1.
        List<Stage_Dostawa> newSourceSupplies =
                sourceSupplies
                        .stream()
                        .filter(supply -> (supply.getTimestampFrom().after(lastImport) || (supply.getTimestampTo() != null && supply.getTimestampTo().after(lastImport))))
                        .collect(Collectors.toList());

        for (Stage_Dostawa sourceSupply : newSourceSupplies) {

            TMP_F_Dostawa factSupply = new TMP_F_Dostawa();
            if (sourceSupply.getTimestampTo() == null) {

                //2 1* a) START
                reflectionUtils.transformFields(sourceSupply, factSupply);

                //przypuszczam, że timestamy się ładnie nie przepiszą
                factSupply.setTimestampFrom(sourceSupply.getTimestampFrom());
                factSupply.setTimestampTo(sourceSupply.getTimestampTo());
                //2 1* a) KONIEC

                //2 1* b)
                factSupply.setDataDostawyId(warehouseIdsGetter.getWarehouseDataId(sourceSupply.getDataDostawy()));
                factSupply.setDataZaplatyId(warehouseIdsGetter.getWarehouseDataId(sourceSupply.getDataZaplaty()));

                factSupply.setProduktId(warehouseIdsGetter.getWarehouseProductId(sourceSupply.getKodKreskowy()));

                //2 2* a)
                checkForWarehouseEntity(factSupply);

                tmp_f_dostawaRepository.save(factSupply);

                //2 1* c)
                StageToWarehouseIdMap idMap = new StageToWarehouseIdMap();
                idMap.setStageId(sourceSupply.getId());
                idMap.setStageTableName(sourceSupply.getClass().getSimpleName());
                idMap.setWarehouseId(factSupply.getDataDostawyId());
                idMap.setWarehouseTableName(factSupply.getClass().getSimpleName());
                stageToWarehouseIdMapRepository.save(idMap);
            } else {
                //musi kurde być
                checkForWarehouseEntity(factSupply);
            }

        }

        logger.logImport(TMP_F_Dostawa.class.getSimpleName(), new Timestamp(System.currentTimeMillis()), true);
    }

    private void checkForWarehouseEntity(TMP_F_Dostawa factSupply) {
        TMP_F_Dostawa lastWarehouseSupply = tmp_f_dostawaRepository.findByNrDokumentuDostawyAndPozycjaDokumentuAndTimestampToIsNull(factSupply.getNrDokumentuDostawy(),factSupply.getPozycjaDokumentu());
        if (null != lastWarehouseSupply) {
            //2 2* b)
            lastWarehouseSupply.setTimestampTo(new Timestamp(System.currentTimeMillis()));
            tmp_f_dostawaRepository.save(lastWarehouseSupply);
        }
    }

}
