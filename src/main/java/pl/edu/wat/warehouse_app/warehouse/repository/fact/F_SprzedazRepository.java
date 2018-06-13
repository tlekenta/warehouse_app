package pl.edu.wat.warehouse_app.warehouse.repository.fact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.warehouse_app.warehouse.model.fact.F_Sprzedaz;

@Repository
public interface F_SprzedazRepository extends JpaRepository<F_Sprzedaz, Long> {
}
