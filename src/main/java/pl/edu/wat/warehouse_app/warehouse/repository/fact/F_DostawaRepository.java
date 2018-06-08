package pl.edu.wat.warehouse_app.warehouse.repository.fact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.warehouse_app.warehouse.model.fact.F_Dostawa;
import pl.edu.wat.warehouse_app.warehouse.model.key.F_DostawaKey;

@Repository
public interface F_DostawaRepository extends JpaRepository<F_Dostawa, F_DostawaKey> {
}
