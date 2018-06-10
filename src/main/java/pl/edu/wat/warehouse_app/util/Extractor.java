package pl.edu.wat.warehouse_app.util;

import lombok.AllArgsConstructor;
import net.sf.jsefa.Deserializer;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.csv.config.CsvConfiguration;
import org.reflections.Reflections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;
import pl.edu.wat.warehouse_app.stage.model.Stage_Dostawa;
import pl.edu.wat.warehouse_app.stage.repository.Stage_DostawaRepository;
import pl.edu.wat.warehouse_app.util.converter.FloatConverter;
import pl.edu.wat.warehouse_app.util.converter.IntegerConverter;
import pl.edu.wat.warehouse_app.util.converter.TimeStampConverter;

import javax.persistence.Entity;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class Extractor {

    RepositoryFactory repositoryFactory;

    Stage_DostawaRepository dostawaRepository;

    StageEntityFactory stageEntityFactory;

    ReflectionUtils reflectionUtils;

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
                dostawa.setCreationTime(new Timestamp(System.currentTimeMillis()));
                dostawa.setImportTime(new Timestamp(System.currentTimeMillis()));
                dostawaRepository.save(dostawa);
            }
        }
        deserializer.close(true);
    }

    private void extract(Class pClass) throws IllegalAccessException, NoSuchFieldException {
        JpaRepository vSourceRepository = repositoryFactory.getSourceRepository(pClass);
        JpaRepository vStageRepository = repositoryFactory.getStageRepository(pClass);

        List vSourceObjects = vSourceRepository.findAll();

        for (Object iSourceObject : vSourceObjects) {
            IStageEntity vTargetObject = stageEntityFactory.getStageEntity(iSourceObject);

            reflectionUtils.rewriteFields(iSourceObject, vTargetObject);

            vTargetObject.setCreationTime(new Timestamp(System.currentTimeMillis()));
            vTargetObject.setImportTime(new Timestamp(System.currentTimeMillis()));

            vStageRepository.save(vTargetObject);
        }

    }

}
