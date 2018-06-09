package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Klient;

public interface Stage_W_KlientRepository extends JpaRepository<Stage_W_Klient, Long> {

    Stage_W_Klient findByNumerKlienta(String pesel);

}
