package pl.edu.wat.warehouse_app.util;

import lombok.AllArgsConstructor;
import org.reflections.Reflections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class Extractor {

    RepositoryFactory repositoryFactory;

    StageEntityFactory stageEntityFactory;

    ReflectionUtils reflectionUtils;

    public void extractZrodloPos() throws IllegalAccessException, NoSuchFieldException {
        Reflections vReflections = new Reflections("pl.edu.wat.warehouse_app.zrodlo_pos.model");
        Set<Class<?>> vClasses = vReflections.getTypesAnnotatedWith(Entity.class);

        for(Class iClass: vClasses) {
            extract(iClass);
        }
    }

    private void extract(Class pClass) throws IllegalAccessException, NoSuchFieldException {
        JpaRepository vSourceRepository = repositoryFactory.getSourceRepository(pClass);
        JpaRepository vStageRepository = repositoryFactory.getStageRepository(pClass);

        List vSourceObjects = vSourceRepository.findAll();

        for(Object iSourceObject: vSourceObjects) {
            IStageEntity vTargetObject = stageEntityFactory.getStageEntity(iSourceObject);

            reflectionUtils.rewriteFields(iSourceObject, vTargetObject);

            vTargetObject.setCreationTime(new Timestamp(System.currentTimeMillis()));
            vTargetObject.setImportTime(new Timestamp(System.currentTimeMillis()));

            vStageRepository.save(vTargetObject);
        }

    }

}
