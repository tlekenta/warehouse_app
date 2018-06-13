package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_W_Czas;

import java.sql.Timestamp;

public interface TMP_W_CzasRepository extends JpaRepository<TMP_W_Czas, Long> {

    TMP_W_Czas findByDateTime(Timestamp timestamp);

}
