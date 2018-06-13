package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.SourceToStageIdMap;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_F_Zmiana_Pracownicza;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_W_Czas;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_ObecnoscWPracy;
import pl.edu.wat.warehouse_app.stage.repository.SourceToStageIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.StageToWarehouseIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_F_Zmiana_PracowniczaRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_CzasRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_PracownikRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_SklepRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_ObecnoscWPracyRepository;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ZmianaPracowniczaFactTransformer {

    /*
    1. Pobierz nowe obecności
    2. Wciągnij idki czasu i je przepisz
    3. Wyciągnij odpowiednie idki sklepu i pracownika i je przepisz

    Zakładam, że ten fakt nie może się zdezaktualizować, że usunięcie zmiany  albo zmiany sklepu/prcownika/dat nie mają sensu
     */

    TMP_W_PracownikRepository tmp_w_pracownikRepository;

    TMP_W_SklepRepository tmp_w_sklepRepository;

    Stage_ObecnoscWPracyRepository stage_obecnoscWPracyRepository;

    TMP_W_CzasRepository tmp_w_czasRepository;

    TMP_F_Zmiana_PracowniczaRepository tmp_f_zmiana_pracowniczaRepository;

    SourceToStageIdMapRepository sourceToStageIdMapRepository;

    StageToWarehouseIdMapRepository stageToWarehouseIdMapRepository;

    ReflectionUtils reflectionUtils;

    DbLogger logger;

    public void transform() {

        Timestamp lastImport = logger.getLastImportTimestamp(TMP_F_Zmiana_Pracownicza.class.getSimpleName());

        List<Stage_ObecnoscWPracy> noweObecnosci =
                stage_obecnoscWPracyRepository.
                        findAll().
                        stream().
                        filter(obecnosc -> obecnosc.getTimestampFrom().after(lastImport)).
                        collect(Collectors.toList());

        for(Stage_ObecnoscWPracy nowaObecnosc: noweObecnosci) {
            TMP_F_Zmiana_Pracownicza zmianaToSave = new TMP_F_Zmiana_Pracownicza();

            zmianaToSave.setPracownikId(getWarehouseWorkerId(nowaObecnosc.getPracownikId()));
            zmianaToSave.setSklepId(getWarehouseShopId(nowaObecnosc.getSklepId()));
            zmianaToSave.setDataRozpoczeciaId(getTimeId(nowaObecnosc.getPrzybycie()));
            zmianaToSave.setDataZakonczeniaId(getTimeId(nowaObecnosc.getWyjscie()));
            zmianaToSave.setTimestampFrom(nowaObecnosc.getTimestampFrom());

            tmp_f_zmiana_pracowniczaRepository.save(zmianaToSave);
        }

        logger.logImport(TMP_F_Zmiana_Pracownicza.class.getSimpleName(), new Timestamp(System.currentTimeMillis()), true);

    }

    private Long getWarehouseShopId(Long sourceShopId) {
        SourceToStageIdMap sourceShopMap = sourceToStageIdMapRepository.
                findBySourceIdAndSourceTableName(sourceShopId, "ZrodloSystem_Sklep");

        return stageToWarehouseIdMapRepository.
                findByStageIdAndStageTableName(sourceShopMap.getStageId(), sourceShopMap.getStageTableName()).
                getWarehouseId();
    }

    private Long getWarehouseWorkerId(Long sourceWorkerId) {
        SourceToStageIdMap sourceWorkerMap = sourceToStageIdMapRepository.
                findBySourceIdAndSourceTableName(sourceWorkerId, "ZrodloSystem_Pracownik");

        return stageToWarehouseIdMapRepository.
                findByStageIdAndStageTableName(sourceWorkerMap.getStageId(), sourceWorkerMap.getStageTableName()).
                getWarehouseId();
    }

    private Long getTimeId(Timestamp time) {
        time.setSeconds(0);
        TMP_W_Czas byDateTime = tmp_w_czasRepository.findByDateTime(time);
        return byDateTime.getCzasId();
    }

}
