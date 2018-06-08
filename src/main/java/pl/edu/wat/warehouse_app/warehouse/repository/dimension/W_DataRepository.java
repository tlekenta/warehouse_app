package pl.edu.wat.warehouse_app.warehouse.repository.dimension;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Data;

@Repository
public interface W_DataRepository extends JpaRepository<W_Data, Long> {
}
