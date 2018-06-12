package pl.edu.wat.warehouse_app.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.Stage_Dostawa;

public interface Stage_DostawaRepository extends JpaRepository<Stage_Dostawa, Long> {

    public Stage_Dostawa getByNumerFakturyAndPozycjaFakturyAndTimestampToIsNull(String pNumerFaktury, Integer pPozycjaFaktury);

}
