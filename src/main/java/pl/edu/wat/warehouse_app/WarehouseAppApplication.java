package pl.edu.wat.warehouse_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.wat.warehouse_app.util.DictionaryGenerator;
import pl.edu.wat.warehouse_app.util.Extractor;
import pl.edu.wat.warehouse_app.util.transformer.AddressDimensionTransformer;
import pl.edu.wat.warehouse_app.util.transformer.KlientDimensionTransformer;
import pl.edu.wat.warehouse_app.util.transformer.ProductDimensionTransformer;


@SpringBootApplication
public class WarehouseAppApplication implements CommandLineRunner {

    @Autowired
    Extractor extractor;
    @Autowired
    DictionaryGenerator dictionaryGenerator;
    @Autowired
    ProductDimensionTransformer productDimensionTransformer;
    @Autowired
    KlientDimensionTransformer klientDimensionTransformer;
    @Autowired
    AddressDimensionTransformer addressDimensionTransformer;

    public static void main(String[] args) {
        SpringApplication.run(WarehouseAppApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        extractor.extractZrodloPos();
        extractor.extractZrodloSystem();
        extractor.extractDostawa();

        dictionaryGenerator.generateDateAndTimes();

        productDimensionTransformer.transform();
        addressDimensionTransformer.transform();
        klientDimensionTransformer.transform();
    }
}
