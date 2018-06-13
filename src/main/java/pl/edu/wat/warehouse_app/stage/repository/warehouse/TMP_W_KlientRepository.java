package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_W_Klient;

public interface TMP_W_KlientRepository extends JpaRepository<TMP_W_Klient, Long> {

    TMP_W_Klient findByNumerKlientaAndTimestampToIsNull(String numerKlienta);

}
