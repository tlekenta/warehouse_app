package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.metadata.model.LogImport;
import pl.edu.wat.warehouse_app.metadata.repository.LogImportRepository;
import pl.edu.wat.warehouse_app.stage.model.SourceToStageIdMap;
import pl.edu.wat.warehouse_app.stage.model.StageToWarehouseIdMap;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Adres;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Klient;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Pracownik;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_Adres;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_Pracownik;
import pl.edu.wat.warehouse_app.stage.repository.SourceToStageIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.StageToWarehouseIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_PracownikRepository;
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

    Stage_W_PracownikRepository stage_w_pracownikRepository;

    Stage_AdresRepository stage_adresRepository;

    Stage_W_AdresRepository stage_w_adresRepository;

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
                Stage_W_Pracownik workerToSave = new Stage_W_Pracownik();

                reflectionUtils.transformFields(newWorker, workerToSave);

                workerToSave.setAdresId(getWarehouseAddressId(newWorker.getAdresID()));

                workerToSave.setTimestampTo(newWorker.getTimestampTo());
                workerToSave.setTimestampFrom(newWorker.getTimestampFrom());

                Stage_W_Pracownik lastWorker = stage_w_pracownikRepository.findByNumerPracownikaAndTimestampToIsNull(newWorker.getNumerPracownika());
                if(lastWorker != null) {
                    workerToSave.setTimestampTo(workerToSave.getTimestampTo());
                    stage_w_pracownikRepository.save(workerToSave);
                }

                stage_w_pracownikRepository.save(workerToSave);

                mapId(newWorker, workerToSave);
            }
        }

        logger.logImport(Stage_W_Pracownik.class.getSimpleName(), new Timestamp(System.currentTimeMillis()), true);

    }

    private void mapId(Stage_Pracownik newWorker, Stage_W_Pracownik workerToSave) {
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
        LogImport logImport = logImportRepository.findTopByTableNameAndSuccessIsTrue(Stage_W_Pracownik.class.getSimpleName());
        return (null== logImport)? new Timestamp(0) : logImport.getImportTime();
    }

}
