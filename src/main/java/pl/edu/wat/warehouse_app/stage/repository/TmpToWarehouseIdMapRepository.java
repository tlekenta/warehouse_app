package pl.edu.wat.warehouse_app.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.TmpToWarehouseIdMap;

@Repository
public interface TmpToWarehouseIdMapRepository extends JpaRepository<TmpToWarehouseIdMap, Long> {

    TmpToWarehouseIdMap findByTmpIdAndTmpTableName(Long id, String tableName);

}
