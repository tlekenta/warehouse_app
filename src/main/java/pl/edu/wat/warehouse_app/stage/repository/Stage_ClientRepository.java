package pl.edu.wat.warehouse_app.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.Stage_Client;

public interface Stage_ClientRepository extends JpaRepository<Stage_Client, Long> {
}
