package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.StageToWarehouseIdMap;
import pl.edu.wat.warehouse_app.stage.model.Stage_Dostawa;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_F_Dostawa;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Data;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Produkt;
import pl.edu.wat.warehouse_app.stage.repository.StageToWarehouseIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.Stage_DostawaRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_F_DostawaRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_DataRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_ProductRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_SklepRepository;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DostawaFactTransformer {


    ReflectionUtils reflectionUtils;

    Stage_DostawaRepository stage_dostawaRepository;
    Stage_W_DataRepository stage_w_dataRepository;
    Stage_W_ProductRepository stage_w_productRepository;
    Stage_W_SklepRepository stage_w_sklepRepository;
    Stage_F_DostawaRepository stage_f_dostawaRepository;

    StageToWarehouseIdMapRepository stageToWarehouseIdMapRepository;

    DbLogger logger;

    public void transform() throws IllegalAccessException {

        List<Stage_Dostawa> sourceSupplies = stage_dostawaRepository.findAll();


        Timestamp lastImport = logger.getLastImportTimestamp(Stage_F_Dostawa.class.getSimpleName());

        //1.
        List<Stage_Dostawa> newSourceSupplies =
                sourceSupplies
                        .stream()
                        .filter(supply -> (supply.getTimestampFrom().after(lastImport) || (supply.getTimestampTo() != null && supply.getTimestampTo().after(lastImport))))
                        .collect(Collectors.toList());

        for (Stage_Dostawa sourceSupply : newSourceSupplies) {

            Stage_F_Dostawa factSupply = new Stage_F_Dostawa();
            if (sourceSupply.getTimestampTo() == null) {

                //2 1* a) START
                reflectionUtils.transformFields(sourceSupply, factSupply);

                //przypuszczam, że timestamy się ładnie nie przepiszą
                factSupply.setTimestampFrom(sourceSupply.getTimestampFrom());
                factSupply.setTimestampTo(sourceSupply.getTimestampTo());
                //2 1* a) KONIEC

                //2 1* b)
                factSupply.setDataDostawyId(getDataId(sourceSupply.getDataDostawy()));
                factSupply.setDataZaplatyId(getDataId(sourceSupply.getDataZaplaty()));

                factSupply.setProduktId(getProductId(sourceSupply.getKodKreskowy()));

                //2 2* a)
                checkForWarehouseEntity(factSupply);

                stage_f_dostawaRepository.save(factSupply);

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

        logger.logImport(Stage_F_Dostawa.class.getSimpleName(), new Timestamp(System.currentTimeMillis()), true);
    }

    private void checkForWarehouseEntity(Stage_F_Dostawa factSupply) {
        Stage_F_Dostawa lastWarehouseSupply = stage_f_dostawaRepository.findByNrDokumentuDostawyAndPozycjaDokumentuAndTimestampToIsNull(factSupply.getNrDokumentuDostawy(),factSupply.getPozycjaDokumentu());
        if (null != lastWarehouseSupply) {
            //2 2* b)
            lastWarehouseSupply.setTimestampTo(new Timestamp(System.currentTimeMillis()));
            stage_f_dostawaRepository.save(lastWarehouseSupply);
        }
    }

    private Long getProductId(String kodKreskowy) {
        Stage_W_Produkt produkt = stage_w_productRepository.findByKodKreskowyAndTimestampToIsNull(kodKreskowy);
        return produkt.getProduktId();
    }

    private Long getDataId(Timestamp timestamp) {
        Stage_W_Data data = stage_w_dataRepository.findByRokAndMiesiacAndDzien(timestamp.getYear() + 1900, timestamp.getMonth() + 1, timestamp.getDate());
        return data.getDataId();
    }

}
