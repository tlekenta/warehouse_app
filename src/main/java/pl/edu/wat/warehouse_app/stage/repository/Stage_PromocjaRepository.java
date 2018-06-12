package pl.edu.wat.warehouse_app.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.Stage_Promocja;

public interface Stage_PromocjaRepository extends JpaRepository<Stage_Promocja, Long> {

    Stage_Promocja findByKodKreskowyAndLpAndTimestampToIsNull(String kodKreskowy, Integer lp);

}
