package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Produkt;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_pos.Stage_Product;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_ProductRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_pos.Stage_ProductRepository;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductDimensionTransformer {

    Stage_ProductRepository stage_productRepository;

    Stage_W_ProductRepository stage_w_productRepository;

    ReflectionUtils reflectionUtils;


    public void transform() throws IllegalAccessException {
        List<Stage_Product> sourceProducts = stage_productRepository.findAll();

        for(Stage_Product sourceProduct: sourceProducts) {
            Stage_W_Produkt warehouseProduct = stage_w_productRepository.findByKodKreskowy(sourceProduct.getBarcode());

            if(warehouseProduct == null) {
                warehouseProduct = new Stage_W_Produkt();

                reflectionUtils.transformFields(sourceProduct, warehouseProduct);

                warehouseProduct.setImportTime(new Timestamp(System.currentTimeMillis()));
                warehouseProduct.setCreationTime(new Timestamp(System.currentTimeMillis()));

                stage_w_productRepository.save(warehouseProduct);

            } else {
                if(reflectionUtils.compareAndRewriteFields(sourceProduct, warehouseProduct)) {
                    warehouseProduct.setImportTime(new Timestamp(System.currentTimeMillis()));
                    stage_w_productRepository.save(warehouseProduct);
                }
            }
        }


    }

}
