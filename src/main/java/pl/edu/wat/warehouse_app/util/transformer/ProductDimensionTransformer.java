package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Produkt;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_pos.Stage_Product;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_ProductRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_pos.Stage_ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductDimensionTransformer {

    Stage_ProductRepository stage_productRepository;

    Stage_W_ProductRepository stage_w_productRepository;


    public void transform() {
        List<Stage_Product> sourceProducts = stage_productRepository.findAll();

        for(Stage_Product sourceProduct: sourceProducts) {
            Stage_W_Produkt warehouseProduct = stage_w_productRepository.findByKodKreskowy(sourceProduct.getBarcode());

            if(warehouseProduct == null) {
                warehouseProduct = new Stage_W_Produkt(
                        null,
                        sourceProduct.getName(),
                        sourceProduct.getBarcode(),
                        ((Double) sourceProduct.getValue()).floatValue(),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                );

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
                    warehouseProduct.setImportTime(LocalDateTime.now());
                    stage_w_productRepository.save(warehouseProduct);
                }
            }
        }


    }

}
