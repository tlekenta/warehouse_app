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
                boolean change = false;
                if(!warehouseProduct.getNazwa().equals(sourceProduct.getName())) {
                    change = true;
                    warehouseProduct.setNazwa(sourceProduct.getName());
                }
                if(!warehouseProduct.getCenaJednostkowa().equals(((Double) sourceProduct.getValue()).floatValue())) {
                    change = true;
                    warehouseProduct.setCenaJednostkowa(((Double) sourceProduct.getValue()).floatValue());
                }

                if(change) {
                    warehouseProduct.setImportTime(new Timestamp(System.currentTimeMillis()));
                    stage_w_productRepository.save(warehouseProduct);
                }
            }
        }


    }

}
