package pl.edu.wat.warehouse_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.DictionaryGenerator;
import pl.edu.wat.warehouse_app.util.Extractor;
import pl.edu.wat.warehouse_app.util.loader.Loader;
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
    PromocjaFactTransformer promocjaFactTransformer;
    @Autowired
    SprzedazFactTransformer sprzedazFactTransformer;
    @Autowired
    ZwrotFactTransformer zwrotFactTransformer;
    @Autowired
    ZmianaPracowniczaFactTransformer zmianaPracowniczaFactTransformer;
    @Autowired
    DostawaFactTransformer dostawaFactTransformer;
    @Autowired
    DictionaryGenerator dictionaryGenerator;
    @Autowired
    DbLogger logger;
    @Autowired
    Loader loader;

    public static void main(String[] args) {
        SpringApplication.run(WarehouseAppApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        extractor.extractZrodloPos();
        extractor.extractZrodloSystem();
        extractor.extractDostawa();
        extractor.extractPromocja();

        if(!dictionaryGenerator.isDateTimeDictionaryGenerated())
            dictionaryGenerator.generateDateAndTimes();

        addressDimensionTransformer.transform();
        pracownikDimensionTransformer.transform();
        klientDimensionTransformer.transform();
        productDimensionTransformer.transform();
        sklepDimensionTransformer.transform();
        sprzedazFactTransformer.transform();
        zmianaPracowniczaFactTransformer.transform();
        zwrotFactTransformer.transform();
        promocjaFactTransformer.transform();
        dostawaFactTransformer.transform();

        logger.logStage();

        loader.loadWarehouse();

        logger.logWarehouse();
    }
}
