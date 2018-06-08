package pl.edu.wat.warehouse_app.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.Stage_Product;

public interface Stage_ProductRepository extends JpaRepository<Stage_Product, Long> {
}
