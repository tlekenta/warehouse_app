package pl.edu.wat.warehouse_app.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.Stage_W_Pracownik;

public interface Stage_W_PracownikRepository extends JpaRepository<Stage_W_Pracownik, Long> {
}
