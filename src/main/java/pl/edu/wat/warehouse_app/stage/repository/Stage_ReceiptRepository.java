package pl.edu.wat.warehouse_app.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.Stage_Receipt;

public interface Stage_ReceiptRepository extends JpaRepository<Stage_Receipt, Long> {
}
