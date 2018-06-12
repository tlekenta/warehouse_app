package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Data;

public interface Stage_W_DataRepository extends JpaRepository<Stage_W_Data, Long> {
    Stage_W_Data findByRokAndMiesiacAndDzien(Integer rok, Integer mniesiac, Integer dzien);
}
