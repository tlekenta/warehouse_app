package pl.edu.wat.warehouse_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.wat.warehouse_app.util.DictionaryGenerator;
import pl.edu.wat.warehouse_app.util.Extractor;
import pl.edu.wat.warehouse_app.util.transformer.*;


@SpringBootApplication
public class WarehouseAppApplication implements CommandLineRunner {

    @Autowired
    Extractor extractor;
    @Autowired
    AddressDimensionTransformer addressDimensionTransformer;
    @Autowired
    PracownikDimensionTransformer pracownikDimensionTransformer;
    @Autowired
    KlientDimensionTransformer klientDimensionTransformer;
    @Autowired
    ProductDimensionTransformer productDimensionTransformer;
    @Autowired
    SklepDimensionTransformer sklepDimensionTransformer;
    @Autowired
    DictionaryGenerator dictionaryGenerator;

    public static void main(String[] args) {
        SpringApplication.run(WarehouseAppApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        extractor.extractZrodloPos();
        extractor.extractZrodloSystem();
        extractor.extractDostawa();

        dictionaryGenerator.generateDateAndTimes();

        addressDimensionTransformer.transform();
        pracownikDimensionTransformer.transform();
        klientDimensionTransformer.transform();
        productDimensionTransformer.transform();
        sklepDimensionTransformer.transform();
    }
}
