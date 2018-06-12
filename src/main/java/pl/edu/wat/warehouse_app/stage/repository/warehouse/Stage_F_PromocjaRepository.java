package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_F_Promocja;

public interface Stage_F_PromocjaRepository extends JpaRepository<Stage_F_Promocja, Long> {
    Stage_F_Promocja findByDataKoncowaIdAndDataPoczatkowaIdAndProduktIdAndTimestampToIsNull(Long dataDoId, Long dataOdId, Long produktId);
}
