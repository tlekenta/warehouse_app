package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_F_Promocja;

public interface TMP_F_PromocjaRepository extends JpaRepository<TMP_F_Promocja, Long> {
    TMP_F_Promocja findByProduktIdAndLpAndTimestampToIsNull(Long produktId, Integer lp);
}
