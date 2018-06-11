package pl.edu.wat.warehouse_app.metadata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.warehouse_app.metadata.model.LogWarehouse;

@Repository
public interface LogWarehouseRepository extends JpaRepository<LogWarehouse, Long> {

}
