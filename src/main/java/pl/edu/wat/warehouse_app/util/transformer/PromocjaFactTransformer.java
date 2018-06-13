package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.StageToWarehouseIdMap;
import pl.edu.wat.warehouse_app.stage.model.Stage_Promocja;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_F_Promocja;
import pl.edu.wat.warehouse_app.stage.repository.StageToWarehouseIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.Stage_PromocjaRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_F_PromocjaRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_DataRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_ProductRepository;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;
import pl.edu.wat.warehouse_app.util.helpers.WarehouseIdsGetter;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PromocjaFactTransformer {

    ReflectionUtils reflectionUtils;

    Stage_PromocjaRepository stage_promocjaRepository;
    TMP_F_PromocjaRepository tmp_f_promocjaRepository;
    TMP_W_DataRepository tmp_w_dataRepository;
    TMP_W_ProductRepository tmp_w_productRepository;
    StageToWarehouseIdMapRepository stageToWarehouseIdMapRepository;


    DbLogger logger;
    WarehouseIdsGetter warehouseIdsGetter;

    public void transform() throws IllegalAccessException {

        List<Stage_Promocja> sourceSales = stage_promocjaRepository.findAll();


        Timestamp lastImport = logger.getLastImportTimestamp(TMP_F_Promocja.class.getSimpleName());

        //1.
        List<Stage_Promocja> newSourceSales =
                sourceSales
                        .stream()
                        .filter(sale -> (sale.getTimestampFrom().after(lastImport) || (sale.getTimestampTo() != null && sale.getTimestampTo().after(lastImport))))
                        .collect(Collectors.toList());

        for(Stage_Promocja sourceSale: newSourceSales) {

            TMP_F_Promocja factSale = new TMP_F_Promocja();
            if (sourceSale.getTimestampTo() == null) {

                //2 1* a) START
                reflectionUtils.transformFields(sourceSale, factSale);

                //przypuszczam, że timestamy się ładnie nie przepiszą
                factSale.setTimestampFrom(sourceSale.getTimestampFrom());
                factSale.setTimestampTo(sourceSale.getTimestampTo());
                //2 1* a) KONIEC

                //2 1* b)
                factSale.setDataPoczatkowaId(warehouseIdsGetter.getWarehouseDataId(sourceSale.getDataPromocjiOd()));
                factSale.setDataKoncowaId(warehouseIdsGetter.getWarehouseDataId(sourceSale.getDataPromocjiDo()));

                factSale.setProduktId(warehouseIdsGetter.getWarehouseProductId(sourceSale.getKodKreskowy()));

                //2 2* a)
                checkForWarehouseEntity(factSale);

                tmp_f_promocjaRepository.save(factSale);

                //2 1* c)
                StageToWarehouseIdMap idMap = new StageToWarehouseIdMap();
                idMap.setStageId(sourceSale.getId());
                idMap.setStageTableName(sourceSale.getClass().getSimpleName());
                idMap.setWarehouseId(factSale.getId());
                idMap.setWarehouseTableName(factSale.getClass().getSimpleName());
                stageToWarehouseIdMapRepository.save(idMap);
            } else {
                //musi kurde być
                checkForWarehouseEntity(factSale);
            }

        }

        logger.logImport(TMP_F_Promocja.class.getSimpleName(),new Timestamp(System.currentTimeMillis()), true);
    }


    private void checkForWarehouseEntity(TMP_F_Promocja factSale) {
        TMP_F_Promocja lastWarehouseSupply = tmp_f_promocjaRepository.findByProduktIdAndLpAndTimestampToIsNull(factSale.getProduktId(),factSale.getLp());
        if (null != lastWarehouseSupply) {
            //2 2* b)
            lastWarehouseSupply.setTimestampTo(new Timestamp(System.currentTimeMillis()));
            tmp_f_promocjaRepository.save(lastWarehouseSupply);
        }
    }

}

