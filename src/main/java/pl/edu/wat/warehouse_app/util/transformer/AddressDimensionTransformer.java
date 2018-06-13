package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.metadata.repository.LogImportRepository;
import pl.edu.wat.warehouse_app.stage.model.StageToWarehouseIdMap;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_W_Adres;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_Adres;
import pl.edu.wat.warehouse_app.stage.repository.StageToWarehouseIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_AdresRepository;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressDimensionTransformer {

    private static final Timestamp OST_IMPORT = new Timestamp(System.currentTimeMillis() - 100000);

    Stage_AdresRepository stage_adresRepository;

    TMP_W_AdresRepository tmp_w_adresRepository;

    ReflectionUtils reflectionUtils;

    StageToWarehouseIdMapRepository stageToWarehouseIdMapRepository;

    DbLogger logger;

    LogImportRepository logImportRepository;

    public void transform() throws IllegalAccessException {
        List<Stage_Adres> sourceAddresses = stage_adresRepository.findAll();

        Timestamp lastImport = logger.getLastImportTimestamp(TMP_W_Adres.class.getSimpleName());

        //1.
        List<Stage_Adres> newAddresses =
                sourceAddresses
                        .stream()
                        .filter(address -> (address.getTimestampFrom().after(lastImport) || (address.getTimestampTo() != null && address.getTimestampTo().after(lastImport))))
                        .collect(Collectors.toList());

        for(Stage_Adres newAddress: newAddresses) {
            TMP_W_Adres addressToSave = new TMP_W_Adres();

            if(newAddress.getTimestampTo() == null) {
                //2 1* a) START
                reflectionUtils.transformFields(newAddress, addressToSave);

                addressToSave.setTimestampFrom(newAddress.getTimestampFrom());
                addressToSave.setTimestampTo(newAddress.getTimestampTo());
                //2 1* a) KONIEC

                //2 1* b)
                //2 2* a)

                //2 2* b)

                //TODO dokończyć, nie wiem jak to zrobić bo adres nie ma id biznesowego
                //trzeba pewnie przez mapowanie idków jakoś wyciągnąć

                tmp_w_adresRepository.save(addressToSave);

                StageToWarehouseIdMap idMap = new StageToWarehouseIdMap();
                idMap.setStageId(newAddress.getId());
                idMap.setStageTableName(newAddress.getClass().getSimpleName());
                idMap.setWarehouseId(addressToSave.getId());
                idMap.setWarehouseTableName(addressToSave.getClass().getSimpleName());
                stageToWarehouseIdMapRepository.save(idMap);
            }

        }

        logger.logImport(TMP_W_Adres.class.getSimpleName(), new Timestamp(System.currentTimeMillis()), true);

    }
}
