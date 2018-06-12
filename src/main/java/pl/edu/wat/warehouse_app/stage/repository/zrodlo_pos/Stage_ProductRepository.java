package pl.edu.wat.warehouse_app.stage.repository.zrodlo_pos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_pos.Stage_Product;

public interface Stage_ProductRepository extends JpaRepository<Stage_Product, Long> {

    Stage_Product findById(Long id);

}
