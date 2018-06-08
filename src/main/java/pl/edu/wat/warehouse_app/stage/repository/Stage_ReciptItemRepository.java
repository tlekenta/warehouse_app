package pl.edu.wat.warehouse_app.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.Stage_ReciptItem;

public interface Stage_ReciptItemRepository extends JpaRepository<Stage_ReciptItem, Long> {
}
