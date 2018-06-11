package pl.edu.wat.warehouse_app.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.warehouse_app.stage.model.StageToWarehouseIdMap;

@Repository
public interface StageToWarehouseIdMapRepository extends JpaRepository<StageToWarehouseIdMap, Long> {

    StageToWarehouseIdMap findByStageIdAndStageTableName(Long stageId, String stageTableName);

}
