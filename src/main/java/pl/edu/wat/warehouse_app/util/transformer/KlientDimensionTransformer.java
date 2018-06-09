package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Adres;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Klient;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_Adres;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_Klient;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_KlientRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_KlientRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class KlientDimensionTransformer {

    Stage_W_AdresRepository stage_w_adresRepository;

    Stage_W_KlientRepository stage_w_klientRepository;

    Stage_AdresRepository stage_adresRepository;

    Stage_KlientRepository stage_klientRepository;

    public void transform() {
        List<Stage_Klient> sourceClients = stage_klientRepository.findAll();

        for(Stage_Klient sourceClient: sourceClients) {

            Stage_W_Klient warehouseClient = stage_w_klientRepository.findByNumerKlienta(sourceClient.getNumerKlienta());

            if(warehouseClient == null) {
                //zrob nowego i zapisz
                warehouseClient = new Stage_W_Klient(
                        null,
                        sourceClient.getNumerKlienta(),
                        sourceClient.getImie(),
                        sourceClient.getNazwisko(),
                        sourceClient.getTelefon(),
                        getWarehouseAddress(sourceClient.getAdresId()).getAdresId(),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                );
                stage_w_klientRepository.save(warehouseClient);
            } else {
                //sprawdź czy coś się zmieniło, jeżeli tak to zaktualizauj, zaktualizuj datę importu i zapisz
                boolean change = false;

                if(!warehouseClient.getImie().equals(sourceClient.getImie())) {
                    change = true;
                    warehouseClient.setImie(sourceClient.getImie());
                }
                if(!warehouseClient.getNazwisko().equals(sourceClient.getNazwisko())) {
                    change = true;
                    warehouseClient.setNazwisko(sourceClient.getNazwisko());
                }
                if(!warehouseClient.getNumerTelefonu().equals(sourceClient.getTelefon())) {
                    change = true;
                    warehouseClient.setNumerTelefonu(sourceClient.getTelefon());
                }
                if(!warehouseClient.getAdresId().equals(getWarehouseAddress(sourceClient.getAdresId()).getAdresId())) {
                    change = true;
                    warehouseClient.setAdresId(getWarehouseAddress(sourceClient.getAdresId()).getAdresId());
                }

                if(change) {
                   warehouseClient.setImportTime(LocalDateTime.now());
                   stage_w_klientRepository.save(warehouseClient);
                }

            }
        }

    }

    private Stage_W_Adres getWarehouseAddress(Long sourceAddressId) {
        Stage_Adres sourceAddress = stage_adresRepository.findOne(sourceAddressId);
        return stage_w_adresRepository.findByUlicaAndNumerDomuAndNumerMieszkaniaAndKodPocztowyAndMiastoAndPoczta(
                sourceAddress.getUlica(),
                sourceAddress.getNumerBudynku(),
                sourceAddress.getNumerLokalu(),
                sourceAddress.getKodPocztowy(),
                sourceAddress.getMiasto(),
                sourceAddress.getPoczta()
        );

    }

}
