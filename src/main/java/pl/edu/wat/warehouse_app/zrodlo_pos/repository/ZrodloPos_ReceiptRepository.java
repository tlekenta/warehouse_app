package pl.edu.wat.warehouse_app.zrodlo_pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.zrodlo_pos.model.ZrodloPos_Receipt;

public interface ZrodloPos_ReceiptRepository extends JpaRepository<ZrodloPos_Receipt, Long> {
}
