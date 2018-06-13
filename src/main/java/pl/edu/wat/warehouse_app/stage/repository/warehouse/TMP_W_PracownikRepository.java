package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_W_Pracownik;

import java.util.List;

public interface TMP_W_PracownikRepository extends JpaRepository<TMP_W_Pracownik, Long> {

    List<TMP_W_Pracownik> findByNumerPracownika(String numerPracownika);

    TMP_W_Pracownik findByNumerPracownikaAndTimestampToIsNull(String numerPracownika);

}
