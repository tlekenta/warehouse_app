package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.metadata.repository.LogImportRepository;
import pl.edu.wat.warehouse_app.stage.model.SourceToStageIdMap;
import pl.edu.wat.warehouse_app.stage.model.StageToWarehouseIdMap;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_W_Pracownik;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_Pracownik;
import pl.edu.wat.warehouse_app.stage.repository.SourceToStageIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.StageToWarehouseIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_PracownikRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_PracownikRepository;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PracownikDimensionTransformer {

    Stage_PracownikRepository stage_pracownikRepository;

    TMP_W_PracownikRepository tmp_w_pracownikRepository;

    Stage_AdresRepository stage_adresRepository;

    TMP_W_AdresRepository tmp_w_adresRepository;

    ReflectionUtils reflectionUtils;

    SourceToStageIdMapRepository sourceToStageIdMapRepository;

    StageToWarehouseIdMapRepository stageToWarehouseIdMapRepository;

    DbLogger logger;

    LogImportRepository logImportRepository;

    public void transform() throws IllegalAccessException {
        List<Stage_Pracownik> sourceWorkers = stage_pracownikRepository.findAll();

        Timestamp lastImport = getLastImportTimestamp();

        List<Stage_Pracownik> newWorkers =
                sourceWorkers.
                        stream().
                        filter(worker -> (worker.getTimestampFrom().after(lastImport) || (worker.getTimestampTo() != null && worker.getTimestampTo().after(lastImport)))).
                        collect(Collectors.toList());

        for(Stage_Pracownik newWorker: newWorkers) {
            if(newWorker.getTimestampTo() == null) {
                TMP_W_Pracownik workerToSave = new TMP_W_Pracownik();

                reflectionUtils.transformFields(newWorker, workerToSave);

                workerToSave.setAdresId(getWarehouseAddressId(newWorker.getAdresID()));

                workerToSave.setTimestampTo(newWorker.getTimestampTo());
                workerToSave.setTimestampFrom(newWorker.getTimestampFrom());

                TMP_W_Pracownik lastWorker = tmp_w_pracownikRepository.findByNumerPracownikaAndTimestampToIsNull(newWorker.getNumerPracownika());
                if(lastWorker != null) {
                    lastWorker.setTimestampTo(new Timestamp(System.currentTimeMillis()));
                    tmp_w_pracownikRepository.save(lastWorker);
                }

                tmp_w_pracownikRepository.save(workerToSave);

                mapId(newWorker, workerToSave);
            } else {
                //ten els też musi być bo inaczej nie będzie obsłużone usuwanie pracowników
                TMP_W_Pracownik lastWorker = tmp_w_pracownikRepository.findByNumerPracownikaAndTimestampToIsNull(newWorker.getNumerPracownika());
                if(lastWorker != null) {
                    lastWorker.setTimestampTo(new Timestamp(System.currentTimeMillis()));
                    tmp_w_pracownikRepository.save(lastWorker);
                }
            }
        }

        logger.logImport(TMP_W_Pracownik.class.getSimpleName(), new Timestamp(System.currentTimeMillis()), true);

    }

    private void mapId(Stage_Pracownik newWorker, TMP_W_Pracownik workerToSave) {
        StageToWarehouseIdMap idMap = new StageToWarehouseIdMap();

        idMap.setStageId(newWorker.getId());
        idMap.setStageTableName(newWorker.getClass().getSimpleName());
        idMap.setWarehouseId(workerToSave.getPracownikId());
        idMap.setWarehouseTableName(workerToSave.getClass().getSimpleName());

        stageToWarehouseIdMapRepository.save(idMap);
    }

    private Long getWarehouseAddressId(Long sourceAddressId) {
        SourceToStageIdMap sourceAddressMap = sourceToStageIdMapRepository.
                findBySourceIdAndSourceTableName(sourceAddressId, "ZrodloSystem_Adres");

        return stageToWarehouseIdMapRepository.
                findByStageIdAndStageTableName(sourceAddressMap.getStageId(), sourceAddressMap.getStageTableName()).
                getWarehouseId();
    }

    private Timestamp getLastImportTimestamp(){
        Timestamp logImport = logImportRepository.findLastTimestampForTable(TMP_W_Pracownik.class.getSimpleName());
        return (null== logImport)? new Timestamp(0) : logImport;
    }

}
