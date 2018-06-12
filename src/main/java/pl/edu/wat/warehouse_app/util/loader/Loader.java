package pl.edu.wat.warehouse_app.util.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.loader.dimentions.*;
import pl.edu.wat.warehouse_app.util.loader.facts.*;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.*;
import pl.edu.wat.warehouse_app.warehouse.model.fact.*;

import java.sql.Timestamp;

@Service
public class Loader {

    @Autowired
    DbLogger logger;
    @Autowired
    DostawaFactLoader dostawaFactLoader;
    @Autowired
    PromocjaFactLoader promocjaFactLoader;
    @Autowired
    SprzedazFactLoader sprzedazFactLoader;
    @Autowired
    ZmianaPracowniczaFactLoader zmianaPracowniczaFactLoader;
    @Autowired
    ZwrotFactLoader zwrotFactLoader;
    @Autowired
    AdresDimensionLoader adresDimensionLoader;
    @Autowired
    CzasDimensionLoader czasDimensionLoader;
    @Autowired
    DataDimensionLoader dataDimensionLoader;
    @Autowired
    KlientDimensionLoader klientDimensionLoader;
    @Autowired
    PracownikDimensionLoader pracownikDimensionLoader;
    @Autowired
    ProductDimensionLoader productDimensionLoader;

    public void loadWarehouse() {
        Class[] warehouseClassNames = {
                F_Dostawa.class,
                F_Promocja.class,
                F_Sprzedaz.class,
                F_Zmiana_Pracownicza.class,
                F_Zwrot.class,
                W_Adres.class,
                W_Czas.class,
                W_Data.class,
                W_Klient.class,
                W_Pracownik.class,
                W_Produkt.class
        };
        loadNewRecordsToTable(warehouseClassNames);
    }

    private void loadNewRecordsToTable(Class[] classes) {
        for (Class className : classes) {
            Timestamp lastImport = logger.getLastImportTimestamp(className.getSimpleName());
            loadTable(className.getSimpleName(), lastImport);
        }
    }

    private void loadTable(String simpleName, Timestamp lastImport) {
        switch (simpleName) {
            case "F_Dostawa":
                dostawaFactLoader.load(lastImport);
                break;
            case "F_Promocja":
                promocjaFactLoader.load(lastImport);
                break;
            case "F_Sprzedaz":
                sprzedazFactLoader.load(lastImport);
                break;
            case "F_Zmiana_Pracownicza":
                zmianaPracowniczaFactLoader.load(lastImport);
                break;
            case "F_Zwrot":
                zwrotFactLoader.load(lastImport);
                break;
            case "W_Adres":
                adresDimensionLoader.load(lastImport);
                break;
            case "W_Czas":
                czasDimensionLoader.load(lastImport);
                break;
            case "W_Data":
                dataDimensionLoader.load(lastImport);
                break;
            case "W_Klient":
                klientDimensionLoader.load(lastImport);
                break;
            case "W_Pracownik":
                pracownikDimensionLoader.load(lastImport);
                break;
            case "W_Produkt":
                productDimensionLoader.load(lastImport);
                break;

        }
    }

}
