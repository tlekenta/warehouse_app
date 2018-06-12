package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Pracownik;

import java.util.List;

public interface Stage_W_PracownikRepository extends JpaRepository<Stage_W_Pracownik, Long> {

    List<Stage_W_Pracownik> findByNumerPracownika(String numerPracownika);

    Stage_W_Pracownik findByNumerPracownikaAndTimestampToIsNull(String numerPracownika);

}
