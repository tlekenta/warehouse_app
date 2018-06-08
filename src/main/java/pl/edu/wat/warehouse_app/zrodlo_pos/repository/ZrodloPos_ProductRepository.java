package pl.edu.wat.warehouse_app.zrodlo_pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.zrodlo_pos.model.ZrodloPos_Product;

public interface ZrodloPos_ProductRepository extends JpaRepository<ZrodloPos_Product, Long> {
}
