package pl.edu.wat.warehouse_app.warehouse.repository.fact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.warehouse_app.warehouse.model.fact.F_Sprzedaz;
import pl.edu.wat.warehouse_app.warehouse.model.key.F_SprzedazKey;

@Repository
public interface F_SprzedazRepository extends JpaRepository<F_Sprzedaz, F_SprzedazKey> {
}
