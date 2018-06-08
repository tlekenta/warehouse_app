package pl.edu.wat.warehouse_app.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.Stage_User;

public interface Stage_UserRepository extends JpaRepository<Stage_User, Long> {
}
