package pl.edu.wat.warehouse_app.warehouse.repository.dimension;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Adres;

@Repository
public interface W_AdresRepository extends JpaRepository<W_Adres, Long> {
}
