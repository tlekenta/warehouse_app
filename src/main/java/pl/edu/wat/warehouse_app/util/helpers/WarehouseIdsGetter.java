package pl.edu.wat.warehouse_app.util.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.SourceToStageIdMap;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_W_Czas;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_W_Data;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_W_Produkt;
import pl.edu.wat.warehouse_app.stage.repository.SourceToStageIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.StageToWarehouseIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_CzasRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_DataRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_ProductRepository;

import java.sql.Timestamp;

@Service
public class WarehouseIdsGetter {

    @Autowired
    TMP_W_CzasRepository tmp_w_czasRepository;
    @Autowired
    TMP_W_DataRepository tmp_w_dataRepository;
    @Autowired
    TMP_W_ProductRepository tmp_w_productRepository;
    @Autowired
    SourceToStageIdMapRepository sourceToStageIdMapRepository;
    @Autowired
    StageToWarehouseIdMapRepository stageToWarehouseIdMapRepository;

    public Long getWarehouseTimeId(Timestamp time) {
        time.setSeconds(0);
        TMP_W_Czas byDateTime = tmp_w_czasRepository.findByDateTime(time);
        return byDateTime.getId();
    }

    public Long getWarehouseProductId(String kodKreskowy) {
        TMP_W_Produkt produkt = tmp_w_productRepository.findByKodKreskowyAndTimestampToIsNull(kodKreskowy);
        return produkt.getId();
    }

    public Long getWarehouseDataId(Timestamp timestamp) {
        TMP_W_Data data = tmp_w_dataRepository.findByRokAndMiesiacAndDzien(timestamp.getYear() + 1900, timestamp.getMonth() + 1, timestamp.getDate());
        return data.getId();
    }


    public Long getWarehouseAddressId(Long sourceAddressId) {
        SourceToStageIdMap sourceAddressMap = sourceToStageIdMapRepository.
                findBySourceIdAndSourceTableName(sourceAddressId, "ZrodloSystem_Adres");

        return stageToWarehouseIdMapRepository.
                findByStageIdAndStageTableName(sourceAddressMap.getStageId(), sourceAddressMap.getStageTableName()).
                getWarehouseId();
    }


    public Long getWarehouseShopId(Long sourceShopId) {
        SourceToStageIdMap sourceShopMap = sourceToStageIdMapRepository.
                findBySourceIdAndSourceTableName(sourceShopId, "ZrodloSystem_Sklep");

        return stageToWarehouseIdMapRepository.
                findByStageIdAndStageTableName(sourceShopMap.getStageId(), sourceShopMap.getStageTableName()).
                getWarehouseId();
    }

    public Long getWarehouseWorkerId(Long sourceWorkerId) {
        SourceToStageIdMap sourceWorkerMap = sourceToStageIdMapRepository.
                findBySourceIdAndSourceTableName(sourceWorkerId, "ZrodloSystem_Pracownik");

        return stageToWarehouseIdMapRepository.
                findByStageIdAndStageTableName(sourceWorkerMap.getStageId(), sourceWorkerMap.getStageTableName()).
                getWarehouseId();
    }


}
