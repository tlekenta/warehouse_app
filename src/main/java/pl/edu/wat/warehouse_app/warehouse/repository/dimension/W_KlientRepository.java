package pl.edu.wat.warehouse_app.warehouse.repository.dimension;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Klient;

@Repository
public interface W_KlientRepository extends JpaRepository<W_Klient, Long> {
}
