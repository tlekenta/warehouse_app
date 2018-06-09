package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Adres;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_Adres;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_AdresRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class AddressDimensionTransformer {

    Stage_AdresRepository stage_adresRepository;

    Stage_W_AdresRepository stage_w_adresRepository;

    public void transform(){
        List<Stage_Adres> sourceAddresses = stage_adresRepository.findAll();

        for(Stage_Adres sourceAddres: sourceAddresses) {
            Stage_W_Adres warehouseAddress = stage_w_adresRepository.findByUlicaAndNumerDomuAndNumerMieszkaniaAndKodPocztowyAndMiastoAndPoczta(
                sourceAddres.getUlica(),
                sourceAddres.getNumerBudynku(),
                sourceAddres.getNumerLokalu(),
                sourceAddres.getKodPocztowy(),
                sourceAddres.getMiasto(),
                sourceAddres.getPoczta()
            );

            if(warehouseAddress == null) {
                warehouseAddress = new Stage_W_Adres(
                    null,
                    sourceAddres.getUlica(),
                    sourceAddres.getNumerBudynku(),
                    sourceAddres.getNumerLokalu(),
                    sourceAddres.getKodPocztowy(),
                    sourceAddres.getMiasto(),
                    sourceAddres.getPoczta(),
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())
                );
                stage_w_adresRepository.save(warehouseAddress);
            }
        }

    }

}
