package pl.edu.wat.warehouse_app.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.warehouse_app.stage.model.SourceToStageIdMap;

@Repository
public interface SourceToStageIdMapRepository extends JpaRepository<SourceToStageIdMap, Long> {

    SourceToStageIdMap findBySourceIdAndSourceTableName(Long sourceId, String sourceTableName);

}
