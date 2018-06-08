package pl.edu.wat.warehouse_app.util;

import lombok.AllArgsConstructor;
import org.reflections.Reflections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class Extractor {

    RepositoryFactory repositoryFactory;

    public void extractZrodloPos() {
        Reflections vReflections = new Reflections("pl.edu.wat.warehouse_app.zrodlo_pos.model");
        Set<Class<? extends Object>> vClasses = vReflections.getTypesAnnotatedWith(Entity.class);

        for(Class iClass: vClasses) {
            extract(iClass);
        }
    }

    private void extract(Class pClass){
        JpaRepository vSourceRepository = repositoryFactory.getSourceRepository(pClass);
        JpaRepository vStageRepository = repositoryFactory.getStageRepository(pClass);

        List vSourceObjects = vSourceRepository.findAll();

        vStageRepository.save(vSourceObjects);

    }

}
