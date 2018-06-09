package pl.edu.wat.warehouse_app.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.Stage_Klient;

public interface Stage_KlientRepository extends JpaRepository<Stage_Klient, Long> {
}
