package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Pracownik;

public interface Stage_W_PracownikRepository extends JpaRepository<Stage_W_Pracownik, Long> {

    public Stage_W_Pracownik findByPesel(String pesel);

}
