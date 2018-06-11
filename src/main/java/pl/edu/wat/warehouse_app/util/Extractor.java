package pl.edu.wat.warehouse_app.util;

import lombok.AllArgsConstructor;
import net.sf.jsefa.Deserializer;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.csv.config.CsvConfiguration;
import org.reflections.Reflections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;
import pl.edu.wat.warehouse_app.stage.model.SourceToStageIdMap;
import pl.edu.wat.warehouse_app.stage.model.Stage_Dostawa;
import pl.edu.wat.warehouse_app.stage.repository.SourceToStageIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.Stage_DostawaRepository;
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

    StageEntityFactory stageEntityFactory;

    ReflectionUtils reflectionUtils;

    SourceToStageIdMapRepository sourceToStageIdMapRepository;

    public void extractZrodloPos() throws IllegalAccessException, NoSuchFieldException {
        Reflections vReflections = new Reflections("pl.edu.wat.warehouse_app.zrodlo_pos.model");
        Set<Class<?>> vClasses = vReflections.getTypesAnnotatedWith(Entity.class);

        for (Class iClass : vClasses) {
            extract(iClass);
        }
    }

    public void extractZrodloSystem() throws IllegalAccessException, NoSuchFieldException {
        Reflections vReflections = new Reflections("pl.edu.wat.warehouse_app.zrodlo_system.model");
        Set<Class<?>> vClasses = vReflections.getTypesAnnotatedWith(Entity.class);

        for (Class iClass : vClasses) {
            extract(iClass);
        }
    }

    public void extractDostawa() {
        CsvConfiguration config = new CsvConfiguration();
        config.setFieldDelimiter(';');
        config.getSimpleTypeConverterProvider().registerConverterType(Float.class, FloatConverter.class);
        config.getSimpleTypeConverterProvider().registerConverterType(Timestamp.class, TimeStampConverter.class);
        config.getSimpleTypeConverterProvider().registerConverterType(Integer.class, IntegerConverter.class);


        Deserializer deserializer = CsvIOFactory.createFactory(config, Stage_Dostawa.class).createDeserializer();
        InputStreamReader fileReader = new InputStreamReader(this.getClass().getResourceAsStream("./HD_Dostawy.csv"));
        deserializer.open(fileReader);

        while (deserializer.hasNext()) {
            Stage_Dostawa dostawa = deserializer.next();

            Stage_Dostawa test = dostawaRepository.getByNumerFakturyAndPozycjaFaktury(dostawa.getNumerFaktury(), dostawa.getPozycjaFaktury());
            if (null == test) {
                dostawa.setTimestampFrom(new Timestamp(System.currentTimeMillis()));
                dostawa.setTimestampTo(new Timestamp(System.currentTimeMillis()));
                dostawaRepository.save(dostawa);
            }
        }
        deserializer.close(true);
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

        while(vSourceObjects.size() > 0) {
            //3a) START
            IBusinessEntity iSourceObject = (IBusinessEntity) vSourceObjects.removeFirst();
            List vStageObjectsWithSameId = (List) vStageObjects //jak to nie walnie błędu to się zdziwię xD
                    .stream()
                    //nie jestem pewien czy filter() nie przefiltruje mi vStageObjects
                    .filter(o -> ((IBusinessEntity) o).getBusinessKey().equals(iSourceObject.getBusinessKey()))
                    .collect(Collectors.toList());
            vStageObjects.removeAll(vStageObjectsWithSameId);
            //3a) KONIEC

            if(vStageObjectsWithSameId.size() == 0) {
                //3a) 1*
                IStageEntity newEntity = stageEntityFactory.getStageEntity(iSourceObject);
                reflectionUtils.rewriteFields(iSourceObject, newEntity);
                newEntity.setTimestampFrom(new Timestamp(System.currentTimeMillis()));
                newEntity.setTimestampTo(null);
                vStageRepository.save(newEntity);
                SourceToStageIdMap idMap = new SourceToStageIdMap();
                idMap.setSourceId(iSourceObject.getId());
                idMap.setSourceTableName(iSourceObject.getClass().getSimpleName());
                idMap.setStageId(((IBusinessEntity)newEntity).getId());
                idMap.setStageTableName(newEntity.getClass().getSimpleName());
                sourceToStageIdMapRepository.save(idMap);
            } else {
                //3a) 2*
                for(IStageEntity actualRecord: (List<IStageEntity>) vStageObjectsWithSameId) {
                    if(actualRecord.getTimestampTo() == null) {
                        if(reflectionUtils.compareFields(iSourceObject, actualRecord)) {
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
                            SourceToStageIdMap idMap = new SourceToStageIdMap();
                            idMap.setSourceId(iSourceObject.getId());
                            idMap.setSourceTableName(iSourceObject.getClass().getSimpleName());
                            idMap.setStageId(((IBusinessEntity)newEntity).getId());
                            idMap.setStageTableName(newEntity.getClass().getSimpleName());
                            sourceToStageIdMapRepository.save(idMap);
                        }
                    }
                }

            }
        }
        //4.
        for(IStageEntity deletedObject: (List<IStageEntity>) vStageObjects) {
            deletedObject.setTimestampTo(new Timestamp(System.currentTimeMillis()));
            vStageRepository.save(deletedObject);
        }

    }

}
