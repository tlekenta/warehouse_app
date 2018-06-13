package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_F_Dostawa;

public interface TMP_F_DostawaRepository extends JpaRepository<TMP_F_Dostawa, Long> {

    TMP_F_Dostawa findByNrDokumentuDostawyAndPozycjaDokumentuAndTimestampToIsNull(String pNumerFaktury, Integer pPozycjaFaktury);
}
