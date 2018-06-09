package pl.edu.wat.warehouse_app.zrodlo_pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.zrodlo_pos.model.ZrodloPos_ReceiptItem;

public interface ZrodloPos_ReceiptItemRepository extends JpaRepository<ZrodloPos_ReceiptItem, Long> {
}
