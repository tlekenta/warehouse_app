package pl.edu.wat.warehouse_app.util;

import lombok.AllArgsConstructor;
import net.sf.jsefa.Deserializer;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.csv.config.CsvConfiguration;
import org.reflections.Reflections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.*;
import pl.edu.wat.warehouse_app.stage.repository.SourceToStageIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.Stage_DostawaRepository;
import pl.edu.wat.warehouse_app.stage.repository.Stage_PromocjaRepository;
import pl.edu.wat.warehouse_app.util.comparator.StageDostawaComparator;
import pl.edu.wat.warehouse_app.util.comparator.StagePromocjaComparator;
import pl.edu.wat.warehouse_app.util.converter.FloatConverter;
import pl.edu.wat.warehouse_app.util.converter.IntegerConverter;
import pl.edu.wat.warehouse_app.util.converter.TimeStampConverter;

import javax.persistence.Entity;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Extractor {

    RepositoryFactory repositoryFactory;

    Stage_DostawaRepository dostawaRepository;

    Stage_PromocjaRepository promocjaRepository;

    StageEntityFactory stageEntityFactory;

    ReflectionUtils reflectionUtils;

    SourceToStageIdMapRepository sourceToStageIdMapRepository;

    StagePromocjaComparator stagePromocjaComparator;

    StageDostawaComparator stageDostawaComparator;

    DbLogger logger;

    public void extractZrodloPos() throws IllegalAccessException, NoSuchFieldException {
        Reflections vReflections = new Reflections("pl.edu.wat.warehouse_app.zrodlo_pos.model");
        Set<Class<?>> vClasses = vReflections.getTypesAnnotatedWith(Entity.class);

        for (Class iClass : vClasses) {
            extractAndLogImport(iClass);
        }
    }

    public void extractZrodloSystem() throws IllegalAccessException, NoSuchFieldException {
        Reflections vReflections = new Reflections("pl.edu.wat.warehouse_app.zrodlo_system.model");
        Set<Class<?>> vClasses = vReflections.getTypesAnnotatedWith(Entity.class);

        for (Class iClass : vClasses) {
            extractAndLogImport(iClass);
        }
    }

    public void extractDostawa() {
        try {
            Deserializer deserializer = getDeserializer(Stage_Dostawa.class);
            InputStreamReader fileReader = getFileReader("HD_Dostawy");

            deserializer.open(fileReader);

            while (deserializer.hasNext()) {
                Stage_Dostawa dostawa = deserializer.next();
                dostawa.setTimestampFrom(new Timestamp(System.currentTimeMillis()));

                Stage_Dostawa dostawaWStagu = dostawaRepository.getByNumerFakturyAndPozycjaFakturyAndTimestampToIsNull(dostawa.getNumerFaktury(), dostawa.getPozycjaFaktury());

                if (null != dostawaWStagu) {
                    if (!stageDostawaComparator.modelsEqual(dostawa, dostawaWStagu)) {
                        dostawaWStagu.setTimestampTo(dostawa.getTimestampFrom());
                        dostawaRepository.save(dostawaWStagu);
                        dostawaRepository.save(dostawa);
                    }
                } else {
                    dostawaRepository.save(dostawa);
                }
            }

            deserializer.close(true);
            logger.logImport(Stage_Dostawa.class.getSimpleName(), new Timestamp(System.currentTimeMillis()), true);
        } catch (Exception e) {
            logger.logImport(Stage_Dostawa.class.getSimpleName(), new Timestamp(System.currentTimeMillis()), false);
        }
    }

    public void extractPromocja() {
        try {
            Deserializer deserializer = getDeserializer(Stage_Promocja.class);
            InputStreamReader fileReader = getFileReader("HD_Promocje");

            deserializer.open(fileReader);

            while (deserializer.hasNext()) {
                //Dodawane wszystkie obiekty wygenerowane z pliku
                Stage_Promocja promocja = deserializer.next();
                promocja.setTimestampFrom(new Timestamp(System.currentTimeMillis()));

                Stage_Promocja promocjaWStagu = promocjaRepository.findByKodKreskowyAndLpAndTimestampToIsNull(promocja.getKodKreskowy(), promocja.getLp());

                if (null != promocjaWStagu) {
                    if (!stagePromocjaComparator.modelsEqual(promocja, promocjaWStagu)) {
                        promocjaRepository.save(promocja);
                        promocjaWStagu.setTimestampTo(promocja.getTimestampFrom());
                        promocjaRepository.save(promocjaWStagu);
                    }
                } else {
                    promocjaRepository.save(promocja);
                }
            }
            deserializer.close(true);
            logger.logImport(Stage_Promocja.class.getSimpleName(), new Timestamp(System.currentTimeMillis()), true);
        } catch (Exception $e) {
            logger.logImport(Stage_Promocja.class.getSimpleName(), new Timestamp(System.currentTimeMillis()), false);
        }
    }

    private Deserializer getDeserializer(Class pClass) {
        CsvConfiguration config = new CsvConfiguration();
        config.setFieldDelimiter(';');
        config.getSimpleTypeConverterProvider().registerConverterType(Float.class, FloatConverter.class);
        config.getSimpleTypeConverterProvider().registerConverterType(Timestamp.class, TimeStampConverter.class);
        config.getSimpleTypeConverterProvider().registerConverterType(Integer.class, IntegerConverter.class);
        return CsvIOFactory.createFactory(config, pClass).createDeserializer();
    }

    private InputStreamReader getFileReader(String fileName) {
        return new InputStreamReader(this.getClass().getResourceAsStream("./" + fileName + ".csv"));
    }

    private void extractAndLogImport(Class pClass){
        try {
            extract(pClass);
            logger.logImport(pClass.getSimpleName(), new Timestamp(System.currentTimeMillis()), true);
        } catch (Exception e) {
            logger.logImport(pClass.getSimpleName(), new Timestamp(System.currentTimeMillis()), false);
        }
    }

    private void extract(Class pClass) throws IllegalAccessException, NoSuchFieldException {
        JpaRepository vSourceRepository = repositoryFactory.getSourceRepository(pClass);
        JpaRepository vStageRepository = repositoryFactory.getStageRepository(pClass);

        /*
        1. Pobierz wszystko ze źródła do listy
        2. Pobierz wszytsko ze staga do listy
        3. Dla każdego elementu w źródle
            a) wyjmij z listy stagowej wszytskie elemnty o tym samym id biznesowym
                1* Jeżeli nie ma żadnego elementu o takim id_biz:
                    -utwórz nowy na podstawie obiektu źródłowego
                    -nadaj nowe id główne
                    -czas od ustaw na teraz, czas do na NULL
                    -zapisz
                    -po zapisie zmapuj id źródłowe na id stagowe
                2* Jeżeli jest (są):
                    -znjadź taki, który czas do ma = NULL -> TODO: będzie problem gdy obiekt zostanie usunięty i ponownie dodany
                    -porównaj te obiekty (compare fields z reflectionutils do tego będzie)
                        a) jeżeli są takie same nie rób nic
                        b) jeżeli są różne:
                            -w tym obiekcie stagowym ustaw czas do na teraz
                            -utwórz nowy obiekt stagowy z czasem od = teraz, do = NULL
                            -po zapisie zmapuj id źródłowe na id stagowe
         4. Po pętli elementy, które ewentualnie pozostały są to obiekty, których nie ma teraz w źródle - ustaw czas do na teraz
         */

        LinkedList vSourceObjects = new LinkedList(vSourceRepository.findAll()); //1
        LinkedList vStageObjects = new LinkedList(vStageRepository.findAll()); //2

        while (vSourceObjects.size() > 0) {
            //3a) START
            IBusinessEntity iSourceObject = (IBusinessEntity) vSourceObjects.removeFirst();
            List vStageObjectsWithSameId = (List) vStageObjects //jak to nie walnie błędu to się zdziwię xD
                    .stream()
                    //nie jestem pewien czy filter() nie przefiltruje mi vStageObjects
                    .filter(o -> ((IBusinessEntity) o).getBusinessKey().equals(iSourceObject.getBusinessKey()))
                    .collect(Collectors.toList());
            vStageObjects.removeAll(vStageObjectsWithSameId);
            //3a) KONIEC

            if (vStageObjectsWithSameId.size() == 0) {
                //3a) 1*
                IStageEntity newEntity = stageEntityFactory.getStageEntity(iSourceObject);
                reflectionUtils.rewriteFields(iSourceObject, newEntity);
                newEntity.setTimestampFrom(new Timestamp(System.currentTimeMillis()));
                newEntity.setTimestampTo(null);
                vStageRepository.save(newEntity);
                mapId(iSourceObject, (IBusinessEntity) newEntity);
            } else {
                //3a) 2*
                for (IStageEntity actualRecord : (List<IStageEntity>) vStageObjectsWithSameId) {
                    if (actualRecord.getTimestampTo() == null) {
                        if (reflectionUtils.compareFields(iSourceObject, actualRecord)) {
                            //3a) 2*a)
                        } else {
                            //3a) 2*b)
                            actualRecord.setTimestampTo(new Timestamp(System.currentTimeMillis()));
                            IStageEntity newEntity = stageEntityFactory.getStageEntity(iSourceObject);
                            reflectionUtils.rewriteFields(iSourceObject, newEntity);
                            newEntity.setTimestampFrom(new Timestamp(System.currentTimeMillis()));
                            newEntity.setTimestampTo(null);
                            vStageRepository.save(newEntity);
                            vStageRepository.save(actualRecord);
                            mapId(iSourceObject, (IBusinessEntity) newEntity);
                        }
                    }
                }

            }
        }
        //4.
        for (IStageEntity deletedObject : (List<IStageEntity>) vStageObjects) {
            if (deletedObject.getTimestampTo() == null) {
                deletedObject.setTimestampTo(new Timestamp(System.currentTimeMillis()));
                vStageRepository.save(deletedObject);
            }
        }

    }

    private void mapId(IBusinessEntity iSourceObject, IBusinessEntity newEntity) {
        SourceToStageIdMap oldMap = sourceToStageIdMapRepository
                .findBySourceIdAndSourceTableName(iSourceObject.getId(), iSourceObject.getClass().getSimpleName());

        if (oldMap != null) {
            sourceToStageIdMapRepository.delete(oldMap);
        }

        SourceToStageIdMap idMap = new SourceToStageIdMap();
        idMap.setSourceId(iSourceObject.getId());
        idMap.setSourceTableName(iSourceObject.getClass().getSimpleName());
        idMap.setStageId(newEntity.getId());
        idMap.setStageTableName(newEntity.getClass().getSimpleName());
        sourceToStageIdMapRepository.save(idMap);
    }

}
