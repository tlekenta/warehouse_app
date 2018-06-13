package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_W_Sklep;

public interface TMP_W_SklepRepository extends JpaRepository<TMP_W_Sklep, Long> {

    TMP_W_Sklep findByNumerSklepuAndTimestampToIsNull(String numerSklepu);

}
