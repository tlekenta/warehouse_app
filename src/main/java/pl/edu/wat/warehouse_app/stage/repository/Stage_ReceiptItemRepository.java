package pl.edu.wat.warehouse_app.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.Stage_ReceiptItem;

public interface Stage_ReceiptItemRepository extends JpaRepository<Stage_ReceiptItem, Long> {
}
