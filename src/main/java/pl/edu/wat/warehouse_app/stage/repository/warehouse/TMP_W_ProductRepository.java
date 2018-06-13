package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_W_Produkt;

import java.util.List;

public interface TMP_W_ProductRepository extends JpaRepository<TMP_W_Produkt, Long> {

    List<TMP_W_Produkt> findByKodKreskowy(String kodKreskowy);

    TMP_W_Produkt findByKodKreskowyAndTimestampToIsNull(String kodKreskowy);

}
