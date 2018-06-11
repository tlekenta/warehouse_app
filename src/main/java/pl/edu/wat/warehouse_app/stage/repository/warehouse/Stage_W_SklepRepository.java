package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Sklep;

public interface Stage_W_SklepRepository extends JpaRepository<Stage_W_Sklep, Long> {

    Stage_W_Sklep findByNumerSklepuAndTimestampToIsNull(String numerSklepu);

}
