package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.StageToWarehouseIdMap;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_F_Zwrot;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_pos.Stage_Zwrot;
import pl.edu.wat.warehouse_app.stage.repository.StageToWarehouseIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_F_ZwrotRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_pos.Stage_ZwrotRepository;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;
import pl.edu.wat.warehouse_app.util.helpers.WarehouseIdsGetter;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ZwrotFactTransformer {

    Stage_ZwrotRepository stage_zwrotRepository;
    TMP_F_ZwrotRepository tmp_f_zwrotRepository;

    StageToWarehouseIdMapRepository stageToWarehouseIdMapRepository;

    ReflectionUtils reflectionUtils;

    WarehouseIdsGetter warehouseIdsGetter;
    DbLogger logger;

    public void transform() throws IllegalAccessException {

        List<Stage_Zwrot> sourceZwroty = stage_zwrotRepository.findAll();

        Timestamp lastImport = logger.getLastImportTimestamp(TMP_F_Zwrot.class.getSimpleName());

        //1.
        List<Stage_Zwrot> newZwroty =
                sourceZwroty
                        .stream()
                        .filter(zwrot -> (zwrot.getTimestampFrom().after(lastImport) || (zwrot.getTimestampTo() != null && zwrot.getTimestampTo().after(lastImport))))
                        .collect(Collectors.toList());

        for (Stage_Zwrot newZwrot : newZwroty) {
            TMP_F_Zwrot newWarehouseZwrot = new TMP_F_Zwrot();

            if (newZwrot.getTimestampTo() == null) {
                //2 1* a) START
                reflectionUtils.transformFields(newZwrot, newWarehouseZwrot);

                //przypuszczam, że timestamy się ładnie nie przepiszą
                newWarehouseZwrot.setTimestampFrom(newZwrot.getTimestampFrom());
                newWarehouseZwrot.setTimestampTo(newZwrot.getTimestampTo());
                //2 1* a) KONIEC

                //2 1* b)
                newWarehouseZwrot.setKlientId(warehouseIdsGetter.getWarehouseKlientId(newZwrot.getKlientNumber()));
                newWarehouseZwrot.setProductId(warehouseIdsGetter.getWarehouseProductId(newZwrot.getKodKreskowy()));

                //2 2* a)
                checkForWarehouseEntity(newWarehouseZwrot);
                tmp_f_zwrotRepository.save(newWarehouseZwrot);

                //2 1* c)
                StageToWarehouseIdMap idMap = new StageToWarehouseIdMap();
                idMap.setStageId(newZwrot.getId());
                idMap.setStageTableName(newZwrot.getClass().getSimpleName());
                idMap.setWarehouseId(newWarehouseZwrot.getId());
                idMap.setWarehouseTableName(newWarehouseZwrot.getClass().getSimpleName());
                stageToWarehouseIdMapRepository.save(idMap);

            } else {
                checkForWarehouseEntity(newWarehouseZwrot);
            }
        }

        logger.logImport(TMP_F_Zwrot.class.getSimpleName(), new Timestamp(System.currentTimeMillis()), true);
    }


    private void checkForWarehouseEntity(TMP_F_Zwrot factZwrot) {
        TMP_F_Zwrot lastWarehouseClient = tmp_f_zwrotRepository.findByReceiptCodeAndAndProductIdAndTimestampToIsNull(factZwrot.getReceiptCode(),factZwrot.getProductId());
        if (null != lastWarehouseClient) {
            //2 2* b)
            lastWarehouseClient.setTimestampTo(new Timestamp(System.currentTimeMillis()));
            tmp_f_zwrotRepository.save(lastWarehouseClient);
        }

    }

}
