package pl.edu.wat.warehouse_app.stage.repository.zrodlo_system;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_Pracownik;

public interface Stage_PracownikRepository extends JpaRepository<Stage_Pracownik, Long> {
}
