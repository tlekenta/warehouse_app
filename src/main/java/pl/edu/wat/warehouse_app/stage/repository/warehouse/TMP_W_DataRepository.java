package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_W_Data;

public interface TMP_W_DataRepository extends JpaRepository<TMP_W_Data, Long> {
    TMP_W_Data findByRokAndMiesiacAndDzien(Integer rok, Integer mniesiac, Integer dzien);
}
