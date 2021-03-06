package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.metadata.repository.LogImportRepository;
import pl.edu.wat.warehouse_app.stage.model.StageToWarehouseIdMap;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_W_Klient;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_Klient;
import pl.edu.wat.warehouse_app.stage.repository.SourceToStageIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.StageToWarehouseIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_KlientRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_KlientRepository;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;
import pl.edu.wat.warehouse_app.util.helpers.WarehouseIdsGetter;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KlientDimensionTransformer {
    /*
    Ogólny algorytm dla transformerów: (w źródlanych tabelach stagowych mamy: czas od i czas do)
    1. Weź z tabeli źródlanej te elementy w których czas od lub czas do jest większy niż czas ostatniego importu
    2. Gówno mamy nie gwarancję (gwarancję tego, że obiektu nie ma w hurtowni). Mamy gwarancję, że obiekt został zakutualizowany od ostatniego ładowania.
        1* Jeżeli nowy obiekt ma nullową date do to znaczy, że jest nowy, wtedy:
            a) przepisz pola pomijając Id główne i pozostałe id (w sumie się pomija bo przepisywane są tylko oznaczone pola)
            b) uzupełnij klucze obce odpowiednimi wartościami idków z hurtowni
            c) wstaw nowe mapowanie idków
        2* Jeżeli ma nie nullową, to znaczy,że się zmienił jego stan obowiązywania, wtedy:
            a) wyciągnij taki sam obiekt z bazy (po id biznesowym ??? czy może uznajemy, że id w stage_hurtownia jest takie samo jak w hurtowni ???)
            b) przepisz do niego datę do i zapisz
     */

    TMP_W_AdresRepository tmp_w_adresRepository;

    TMP_W_KlientRepository tmp_w_klientRepository;

    Stage_AdresRepository stage_adresRepository;

    Stage_KlientRepository stage_klientRepository;

    SourceToStageIdMapRepository sourceToStageIdMapRepository;

    StageToWarehouseIdMapRepository stageToWarehouseIdMapRepository;

    DbLogger logger;

    WarehouseIdsGetter warehouseIdsGetter;

    LogImportRepository logImportRepository;

    ReflectionUtils reflectionUtils;

    public void transform() throws IllegalAccessException {
        List<Stage_Klient> sourceClients = stage_klientRepository.findAll();

        Timestamp lastImport = logger.getLastImportTimestamp(TMP_W_Klient.class.getSimpleName());

        //1.
        List<Stage_Klient> newClients =
                sourceClients
                        .stream()
                        .filter(klient -> (klient.getTimestampFrom().after(lastImport) || (klient.getTimestampTo() != null && klient.getTimestampTo().after(lastImport))))
                        .collect(Collectors.toList());

        for (Stage_Klient newClient : newClients) {
            TMP_W_Klient warehouseClient = new TMP_W_Klient();

            if (newClient.getTimestampTo() == null) {
                //2 1* a) START
                reflectionUtils.transformFields(newClient, warehouseClient);

                //przypuszczam, że timestamy się ładnie nie przepiszą
                warehouseClient.setTimestampFrom(newClient.getTimestampFrom());
                warehouseClient.setTimestampTo(newClient.getTimestampTo());
                //2 1* a) KONIEC

                //2 1* b)
                warehouseClient.setAdresId(warehouseIdsGetter.getWarehouseAddressId(newClient.getAdresId()));

                //2 2* a)
                TMP_W_Klient lastWarehouseClient = tmp_w_klientRepository.findByNumerKlientaAndTimestampToIsNull(newClient.getNumerKlienta());
                if (null != lastWarehouseClient) {
                    //2 2* b)
                    lastWarehouseClient.setTimestampTo(new Timestamp(System.currentTimeMillis()));
                    tmp_w_klientRepository.save(lastWarehouseClient);
                }

                tmp_w_klientRepository.save(warehouseClient);

                //2 1* c)
                StageToWarehouseIdMap idMap = new StageToWarehouseIdMap();
                idMap.setStageId(newClient.getId());
                idMap.setStageTableName(newClient.getClass().getSimpleName());
                idMap.setWarehouseId(warehouseClient.getId());
                idMap.setWarehouseTableName(warehouseClient.getClass().getSimpleName());
                stageToWarehouseIdMapRepository.save(idMap);

            } else {
                //musi kurde być
                TMP_W_Klient lastWarehouseClient = tmp_w_klientRepository.findByNumerKlientaAndTimestampToIsNull(newClient.getNumerKlienta());
                if (null != lastWarehouseClient) {
                    lastWarehouseClient.setTimestampTo(new Timestamp(System.currentTimeMillis()));
                    tmp_w_klientRepository.save(lastWarehouseClient);
                }
            }
        }

        logger.logImport(TMP_W_Klient.class.getSimpleName(), new Timestamp(System.currentTimeMillis()), true);

    }

}
