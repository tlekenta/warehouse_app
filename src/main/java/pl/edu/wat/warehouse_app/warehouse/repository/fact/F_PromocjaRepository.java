package pl.edu.wat.warehouse_app.warehouse.repository.fact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.warehouse_app.warehouse.model.fact.F_Promocja;

@Repository
public interface F_PromocjaRepository extends JpaRepository<F_Promocja, Long> {
}
