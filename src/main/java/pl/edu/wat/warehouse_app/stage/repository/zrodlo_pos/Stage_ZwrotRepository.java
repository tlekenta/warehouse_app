package pl.edu.wat.warehouse_app.stage.repository.zrodlo_pos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_pos.Stage_Zwrot;

public interface Stage_ZwrotRepository extends JpaRepository<Stage_Zwrot, Long> {
}
