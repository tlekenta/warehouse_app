package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.metadata.repository.LogImportRepository;
import pl.edu.wat.warehouse_app.stage.model.SourceToStageIdMap;
import pl.edu.wat.warehouse_app.stage.model.StageToWarehouseIdMap;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Sklep;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_Sklep;
import pl.edu.wat.warehouse_app.stage.repository.SourceToStageIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.StageToWarehouseIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_SklepRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_SklepRepository;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SklepDimensionTransformer {

    private static final Timestamp OST_IMPORT = new Timestamp(System.currentTimeMillis() - 100000);

    Stage_SklepRepository stage_sklepRepository;

    Stage_W_SklepRepository stage_w_sklepRepository;

    Stage_AdresRepository stage_adresRepository;

    Stage_W_AdresRepository stage_w_adresRepository;

    SourceToStageIdMapRepository sourceToStageIdMapRepository;

    StageToWarehouseIdMapRepository stageToWarehouseIdMapRepository;

    ReflectionUtils reflectionUtils;

    DbLogger logger;

    LogImportRepository logImportRepository;

    public void transform() throws IllegalAccessException {
        List<Stage_Sklep> sourceShops = stage_sklepRepository.findAll();

        Timestamp lastImport = logger.getLastImportTimestamp(Stage_W_Sklep.class.getSimpleName());

        //1.
        List<Stage_Sklep> newShops =
                sourceShops
                        .stream()
                        .filter(sklep -> (sklep.getTimestampFrom().after(lastImport) || (sklep.getTimestampTo() != null && sklep.getTimestampTo().after(lastImport))))
                        .collect(Collectors.toList());

        for(Stage_Sklep newShop: newShops) {
            Stage_W_Sklep warehouseShop = new Stage_W_Sklep();

            if(newShop.getTimestampTo() == null) {
                //2 1* a) START
                reflectionUtils.transformFields(newShop, warehouseShop);

                //przypuszczam, że timestamy się ładnie nie przepiszą
                warehouseShop.setTimestampFrom(newShop.getTimestampFrom());
                warehouseShop.setTimestampTo(newShop.getTimestampTo());
                //2 1* a) KONIEC

                //2 1* b)
                warehouseShop.setAdresId(getWarehouseAddressId(newShop.getAdresID()));
                //2 2* a)
                Stage_W_Sklep lastWarehouseShop = stage_w_sklepRepository.findByNumerSklepuAndTimestampToIsNull(newShop.getNumerSklepu());
                //2 2* b)
                if(null != lastWarehouseShop) {
                    lastWarehouseShop.setTimestampTo(new Timestamp(System.currentTimeMillis()));
                    stage_w_sklepRepository.save(lastWarehouseShop);
                }
                stage_w_sklepRepository.save(warehouseShop);

                //2 1* c)
                StageToWarehouseIdMap idMap = new StageToWarehouseIdMap();
                idMap.setStageId(newShop.getId());
                idMap.setStageTableName(newShop.getClass().getSimpleName());
                idMap.setWarehouseId(warehouseShop.getSklepId());
                idMap.setWarehouseTableName(warehouseShop.getClass().getSimpleName());
                stageToWarehouseIdMapRepository.save(idMap);

            }
        }

        logger.logImport(Stage_W_Sklep.class.getSimpleName(), new Timestamp(System.currentTimeMillis()), true);

    }


    private Long getWarehouseAddressId(Long sourceAddressId) {
        SourceToStageIdMap sourceAddressMap = sourceToStageIdMapRepository.
                findBySourceIdAndSourceTableName(sourceAddressId, "ZrodloSystem_Adres");

        return stageToWarehouseIdMapRepository.
                findByStageIdAndStageTableName(sourceAddressMap.getStageId(), sourceAddressMap.getStageTableName()).
                getWarehouseId();
    }

        return (null== logImport)? new Timestamp(0) : logImport.getImportTime();
}
