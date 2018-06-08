package pl.edu.wat.warehouse_app.zrodlo_pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.zrodlo_pos.model.ZrodloPos_ReciptItem;

public interface ZrodloPos_ReciptItemRepository extends JpaRepository<ZrodloPos_ReciptItem, Long> {
}
