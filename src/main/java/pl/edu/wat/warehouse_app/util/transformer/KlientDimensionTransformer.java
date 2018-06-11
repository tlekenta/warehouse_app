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
import pl.edu.wat.warehouse_app.util.ReflectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KlientDimensionTransformer {
    /*
    Ogólny algorytm dla transformerów: (w źródlanych tabelach stagowych mamy: czas od i czas do)
    1. Weź z tabeli źródlanej te elementy w których czas od lub czas do jest większy niż czas ostatniego importu
    TODO: zapisywanie czasu ostatniego importu do pliku (chyba wystarczy jak będzie to po prostu czas uruchomienia aplikacji)
    2. Gówno mamy nie gwarancję. Mamy gwarancję, że obiekt został zakutualizowany od ostatniego ładowania.
        1* Jeżeli nowy obiekt ma nullową date do to znaczy, że jest nowy, wtedy:
            a) przepisz pola pomijając Id główne i pozostałe id (w sumie się pomija bo przepisywane są tylko oznaczone pola)
            b) uzupełnij klucze obce odpowiednimi wartościami idków z hurtowni
        2* Jeżeli ma nie nullową, to znaczy,że się zmienił jego stan obowiązywania, wtedy:
            a) wyciągnij taki sam obiekt z bazy (po id biznesowym ??? czy może uznajemy, że id w stage_hurtownia jest takie samo jak w hurtowni ???)
            b) przepisz do niego datę do i zapisz

     TODO: poprawić transformacje klienta i adresu
     */

    private static final Timestamp OST_IMPORT = new Timestamp(System.currentTimeMillis() - 100000);

    Stage_W_AdresRepository stage_w_adresRepository;

    Stage_W_KlientRepository stage_w_klientRepository;

    Stage_AdresRepository stage_adresRepository;

    Stage_KlientRepository stage_klientRepository;

    ReflectionUtils reflectionUtils;

    public void transform() throws IllegalAccessException {
        List<Stage_Klient> sourceClients = stage_klientRepository.findAll();

        //1.
        List<Stage_Klient> newClients =
                sourceClients
                        .stream()
                        .filter(klient -> (klient.getTimestampFrom().after(OST_IMPORT) || klient.getTimestampTo().after(OST_IMPORT)))
                        .collect(Collectors.toList());

        for(Stage_Klient newClient: newClients) {
            Stage_W_Klient warehouseClient = new Stage_W_Klient();

            //2a) START
            reflectionUtils.transformFields(newClient, warehouseClient);

            //przypuszczam, że timestamy się ładnie nie przepiszą
            warehouseClient.setTimestampFrom(newClient.getTimestampFrom());
            warehouseClient.setTimestampTo(newClient.getTimestampTo());
            //2a) KONIEC

            //2b)
            warehouseClient.setAdresId(getWarehouseAddress(newClient.getAdresId()).getAdresId());

            stage_w_klientRepository.save(warehouseClient);
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
