package pl.edu.wat.warehouse_app.stage.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Adres;

public interface Stage_W_AdresRepository extends JpaRepository<Stage_W_Adres, Long> {

    Stage_W_Adres findByUlicaAndNumerDomuAndNumerMieszkaniaAndKodPocztowyAndMiastoAndPoczta(
            String ulica,
            Integer numerDomu,
            Integer numerMieszkania,
            String kodPocztowy,
            String miasto,
            String poczta
    );

}
