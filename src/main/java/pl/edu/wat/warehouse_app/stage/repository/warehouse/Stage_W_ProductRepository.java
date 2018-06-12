package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Produkt;

import java.util.List;

public interface Stage_W_ProductRepository extends JpaRepository<Stage_W_Produkt, Long> {

    List<Stage_W_Produkt> findByKodKreskowy(String kodKreskowy);

    Stage_W_Produkt findByKodKreskowyAndTimestampToIsNull(String kodKreskowy);

}
