package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.StageToWarehouseIdMap;
import pl.edu.wat.warehouse_app.stage.model.Stage_Promocja;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_F_Promocja;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Data;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Produkt;
import pl.edu.wat.warehouse_app.stage.repository.StageToWarehouseIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.Stage_PromocjaRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_F_PromocjaRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_DataRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_ProductRepository;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PromocjaFactTransformer {

    ReflectionUtils reflectionUtils;

    Stage_PromocjaRepository stage_promocjaRepository;
    Stage_F_PromocjaRepository stage_f_promocjaRepository;
    Stage_W_DataRepository stage_w_dataRepository;
    Stage_W_ProductRepository stage_w_productRepository;
    StageToWarehouseIdMapRepository stageToWarehouseIdMapRepository;


    DbLogger logger;

    public void transform() throws IllegalAccessException {

        List<Stage_Promocja> sourceSales = stage_promocjaRepository.findAll();


        Timestamp lastImport = logger.getLastImportTimestamp(Stage_F_Promocja.class.getSimpleName());

        //1.
        List<Stage_Promocja> newSourceSales =
                sourceSales
                        .stream()
                        .filter(sale -> (sale.getTimestampFrom().after(lastImport) || (sale.getTimestampTo() != null && sale.getTimestampTo().after(lastImport))))
                        .collect(Collectors.toList());

        for(Stage_Promocja sourceSale: newSourceSales) {

            Stage_F_Promocja factSale = new Stage_F_Promocja();
            if (sourceSale.getTimestampTo() == null) {

                //2 1* a) START
                reflectionUtils.transformFields(sourceSale, factSale);

                //przypuszczam, że timestamy się ładnie nie przepiszą
                factSale.setTimestampFrom(sourceSale.getTimestampFrom());
                factSale.setTimestampTo(sourceSale.getTimestampTo());
                //2 1* a) KONIEC

                //2 1* b)
                factSale.setDataPoczatkowaId(getDataId(sourceSale.getDataPromocjiOd()));
                factSale.setDataKoncowaId(getDataId(sourceSale.getDataPromocjiDo()));

                factSale.setProduktId(getProductId(sourceSale.getKodKreskowy()));

                //2 2* a)
                checkForWarehouseEntity(factSale);

                stage_f_promocjaRepository.save(factSale);

                //2 1* c)
                StageToWarehouseIdMap idMap = new StageToWarehouseIdMap();
                idMap.setStageId(sourceSale.getId());
                idMap.setStageTableName(sourceSale.getClass().getSimpleName());
                idMap.setWarehouseId(factSale.getPromocjaId());
                idMap.setWarehouseTableName(factSale.getClass().getSimpleName());
                stageToWarehouseIdMapRepository.save(idMap);
            } else {
                //musi kurde być
                checkForWarehouseEntity(factSale);
            }

        }

        logger.logImport(Stage_F_Promocja.class.getSimpleName(),new Timestamp(System.currentTimeMillis()), true);
    }


    private void checkForWarehouseEntity(Stage_F_Promocja factSale) {
        Stage_F_Promocja lastWarehouseSupply = stage_f_promocjaRepository.findByProduktIdAndLpAndTimestampToIsNull(factSale.getProduktId(),factSale.getLp());
        if (null != lastWarehouseSupply) {
            //2 2* b)
            lastWarehouseSupply.setTimestampTo(new Timestamp(System.currentTimeMillis()));
            stage_f_promocjaRepository.save(lastWarehouseSupply);
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

