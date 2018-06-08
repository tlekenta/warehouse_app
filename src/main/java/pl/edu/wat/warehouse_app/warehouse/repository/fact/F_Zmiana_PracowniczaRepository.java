package pl.edu.wat.warehouse_app.warehouse.repository.fact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.warehouse_app.warehouse.model.fact.F_Zmiana_Pracownicza;

@Repository
public interface F_Zmiana_PracowniczaRepository extends JpaRepository<F_Zmiana_Pracownicza, Long> {
}
