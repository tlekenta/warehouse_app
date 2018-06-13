package pl.edu.wat.warehouse_app.util.loader;

import lombok.AllArgsConstructor;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.TmpToWarehouseIdMap;
import pl.edu.wat.warehouse_app.stage.repository.TmpToWarehouseIdMapRepository;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;
import pl.edu.wat.warehouse_app.util.RepositoryFactory;
import pl.edu.wat.warehouse_app.util.WarehouseEntityFactory;
import pl.edu.wat.warehouse_app.util.annotation.ExternalId;
import pl.edu.wat.warehouse_app.util.loader.dimentions.*;
import pl.edu.wat.warehouse_app.util.loader.facts.*;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.*;
import pl.edu.wat.warehouse_app.warehouse.model.fact.*;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Loader {

    DbLogger logger;
    DostawaFactLoader dostawaFactLoader;
    PromocjaFactLoader promocjaFactLoader;
    SprzedazFactLoader sprzedazFactLoader;
    ZmianaPracowniczaFactLoader zmianaPracowniczaFactLoader;
    ZwrotFactLoader zwrotFactLoader;
    AdresDimensionLoader adresDimensionLoader;
    CzasDimensionLoader czasDimensionLoader;
    DataDimensionLoader dataDimensionLoader;
    KlientDimensionLoader klientDimensionLoader;
    PracownikDimensionLoader pracownikDimensionLoader;
    ProductDimensionLoader productDimensionLoader;
    RepositoryFactory repositoryFactory;
    WarehouseEntityFactory warehouseEntityFactory;
    TmpToWarehouseIdMapRepository tmpToWarehouseIdMapRepository;
    ReflectionUtils reflectionUtils;

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

    public void load() throws NoSuchFieldException, IllegalAccessException {
        Reflections vReflections = new Reflections("pl.edu.wat.warehouse_app.stage.model.warehouse");
        Set<Class<?>> vClasses = vReflections.getTypesAnnotatedWith(Entity.class);

        for(Class clazz: vClasses) {
            /*
            Kolejność importów jest ważna!!! (klucze obce)
            1. Adres/Produkt/Czas/Data
            2. Sklep/Pracownik/Klient/Dostawa/Promocja
            3. Zmiana
            4. Sprzedaż

            1. Pobierz z fabryki odpowiednie repozytoria
            2. Z repozytorium stagowego wyjmij wszystkie rekordy
            3. Przefiltruj je po dacie ostatniego importu
            4. Dla każdego elementu w liście:
                a) Wyjmij przy pomocy mapowania odpowiadający rekord z hurtowni
                    1* Jeżeli nie ma mapowania utwórz nową encję, przepisz pola + przy pomocy mapowania ustaw odpowiednie idki dla kluczy obcych + utwórz mapowanie
                    2* Jeżeli jest mapowanie: przepisz timestamp to + zakutalizuj idki dla kluczy obcych
            5. Loguj ładowanie dla klasy hurtownianej

            Utworzyć adnotację, która będzie na klucze obce, jako parametr będzie nazwa tabeli klucza obcego, dzięki niej będę wyciągał z mapy odpowiednie idki hurtowniane
             */

            try {
                JpaRepository stageRepository = repositoryFactory.getStageRepositoryByStageClass(clazz);
                JpaRepository warehouseRepository = repositoryFactory.getWarehouseRepositoryByTmpClass(clazz);

                List<IStageEntity> allStageObjects = (List<IStageEntity>) stageRepository.findAll();

                Timestamp lastImport = logger.getLastImportTimestamp(clazz.getSimpleName() + "_warehouse");

                List<IStageEntity> updatedStageObjects = allStageObjects
                        .stream()
                        .filter(obj -> obj.getTimestampFrom().after(lastImport) || (obj.getTimestampTo() != null && obj.getTimestampTo().after(lastImport)))
                        .collect(Collectors.toList());

                for (IStageEntity updatedStageObject : updatedStageObjects) {
                    TmpToWarehouseIdMap idMap = tmpToWarehouseIdMapRepository
                            .findByTmpIdAndTmpTableName(((IBusinessEntity) updatedStageObject).getId(), updatedStageObject.getClass().getSimpleName());

                    if (idMap == null) {
                        //utwórz nową encję, przepisz pola + przy pomocy mapowania ustaw odpowiednie idki dla kluczy obcych + utwórz mapowanie
                        Object warehouseToSave = warehouseEntityFactory.getWarehouseEntity(updatedStageObject.getClass());
                        reflectionUtils.rewriteFields(updatedStageObject, warehouseToSave);
                        updateExternalId(updatedStageObject, warehouseToSave);

                        warehouseRepository.save(warehouseToSave);

                        idMap = new TmpToWarehouseIdMap();

                        idMap.setTmpId(((IBusinessEntity) updatedStageObject).getId());
                        idMap.setTmpTableName(updatedStageObject.getClass().getSimpleName());
                        idMap.setWarehouseId(((IBusinessEntity) warehouseToSave).getId());
                        idMap.setWarehouseTableName(warehouseToSave.getClass().getSimpleName());

                        tmpToWarehouseIdMapRepository.save(idMap);

                    } else {
                        //wyciągnij po id (tym z mapy) obiekt z hurtowni, przepisz timestamp to + zakutalizuj idki dla kluczy obcych
                        //aktualizacja tutaj kluczy obcych niema sensu (obiekt w tym miejscu na pewno nie został zaktualizowany)
                        Object actualWarehouseObject = warehouseRepository.getOne(idMap.getWarehouseId());
                        ((IStageEntity) actualWarehouseObject).setTimestampTo(new Timestamp(System.currentTimeMillis()));
                        warehouseRepository.save(actualWarehouseObject);
                    }
                }


                logger.logImport(clazz.getSimpleName() + "_warehouse", new Timestamp(System.currentTimeMillis()), true);

            } catch (Exception e) {
                logger.logImport(clazz.getSimpleName() + "_warehouse", new Timestamp(System.currentTimeMillis()), false);
            }
        }

    }

    private void updateExternalId(Object tmpObject, Object warehouseObject) throws IllegalAccessException, NoSuchFieldException {
        Field[] tmpFields = tmpObject.getClass().getDeclaredFields();

        for(Field tmpField: tmpFields) {
            ExternalId annotation = tmpField.getAnnotation(ExternalId.class);
            if(annotation != null) {
                tmpField.setAccessible(true);
                TmpToWarehouseIdMap idMap = tmpToWarehouseIdMapRepository
                        .findByTmpIdAndWarehouseTableName(tmpField.getLong(tmpObject), annotation.tableName());

                String tmpFieldName = tmpField.getName();
                Field warehouseField = warehouseObject.getClass().getDeclaredField(tmpFieldName);

                warehouseField.setAccessible(true);

                warehouseField.set(warehouseObject, idMap.getWarehouseId());

                warehouseField.setAccessible(false);
                tmpField.setAccessible(false);
            }
        }
    }

}
