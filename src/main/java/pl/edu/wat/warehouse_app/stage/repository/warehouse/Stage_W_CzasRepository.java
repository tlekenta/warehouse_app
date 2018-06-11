package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Czas;

import java.sql.Timestamp;

public interface Stage_W_CzasRepository extends JpaRepository<Stage_W_Czas, Long> {

    Stage_W_Czas findByDateTime(Timestamp timestamp);

}
