package pl.edu.wat.warehouse_app.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.SourceToStageIdMap;

public interface SourceToStageIdMapRepository extends JpaRepository<SourceToStageIdMap, Long> {
}
