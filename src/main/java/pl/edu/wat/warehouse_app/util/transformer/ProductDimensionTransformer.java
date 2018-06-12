package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.StageToWarehouseIdMap;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Produkt;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_pos.Stage_Product;
import pl.edu.wat.warehouse_app.stage.repository.StageToWarehouseIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_ProductRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_pos.Stage_ProductRepository;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductDimensionTransformer {

    Stage_ProductRepository stage_productRepository;

    Stage_W_ProductRepository stage_w_productRepository;

    ReflectionUtils reflectionUtils;

    DbLogger logger;

    private StageToWarehouseIdMapRepository stageToWarehouseIdMapRepository;


    public void transform() throws IllegalAccessException {
        List<Stage_Product> sourceProducts = stage_productRepository.findAll();

        Timestamp lastImport = logger.getLastImportTimestamp(Stage_W_Produkt.class.getSimpleName());

        List<Stage_Product> newProducts =
                sourceProducts
                    .stream()
                    .filter(prod -> prod.getTimestampFrom().after(lastImport) || (prod.getTimestampTo() != null && prod.getTimestampTo().after(lastImport)))
                    .collect(Collectors.toList());

        for(Stage_Product newProduct: newProducts) {
            Stage_W_Produkt produktToSave = new Stage_W_Produkt();

            if(newProduct.getTimestampTo() == null) {

                reflectionUtils.transformFields(newProduct, produktToSave);

                produktToSave.setTimestampFrom(newProduct.getTimestampFrom());
                produktToSave.setTimestampTo(newProduct.getTimestampTo());

                //TODO idk jednostki

                Stage_W_Produkt lastProdukt = stage_w_productRepository
                        .findByKodKreskowyAndTimestampToIsNull(newProduct.getBarcode());
                if(lastProdukt != null) {
                    lastProdukt.setTimestampTo(new Timestamp(System.currentTimeMillis()));
                    stage_w_productRepository.save(lastProdukt);
                }

                stage_w_productRepository.save(produktToSave);

                mapId(newProduct, produktToSave);
            } else {
                //ten els też musi być bo inaczej nie będzie obsłużone usuwanie produktów
                Stage_W_Produkt lastProdukt = stage_w_productRepository
                        .findByKodKreskowyAndTimestampToIsNull(newProduct.getBarcode());
                if(lastProdukt != null) {
                    lastProdukt.setTimestampTo(new Timestamp(System.currentTimeMillis()));
                    stage_w_productRepository.save(lastProdukt);
                }
            }
        }

        logger.logImport(Stage_W_Produkt.class.getSimpleName(), new Timestamp(System.currentTimeMillis()), true);

    }

    private void mapId(Stage_Product newProd, Stage_W_Produkt prodToSave) {
        StageToWarehouseIdMap idMap = new StageToWarehouseIdMap();

        idMap.setStageId(newProd.getId());
        idMap.setStageTableName(newProd.getClass().getSimpleName());
        idMap.setWarehouseId(prodToSave.getProduktId());
        idMap.setWarehouseTableName(prodToSave.getClass().getSimpleName());

        stageToWarehouseIdMapRepository.save(idMap);
    }

}
