package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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

    public void transform() throws IllegalAccessException {
        List<Stage_Sklep> sourceShops = stage_sklepRepository.findAll();

        //1.
        List<Stage_Sklep> newShops =
                sourceShops
                        .stream()
                        .filter(sklep -> (sklep.getTimestampFrom().after(OST_IMPORT) || (sklep.getTimestampTo() != null && sklep.getTimestampTo().after(OST_IMPORT))))
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
                stage_w_sklepRepository.save(warehouseShop);

                //2 1* c)
                StageToWarehouseIdMap idMap = new StageToWarehouseIdMap();
                idMap.setStageId(newShop.getId());
                idMap.setStageTableName(newShop.getClass().getSimpleName());
                idMap.setWarehouseId(warehouseShop.getSklepId());
                idMap.setWarehouseTableName(warehouseShop.getClass().getSimpleName());
                stageToWarehouseIdMapRepository.save(idMap);

            } else {
                //2 2* a)
                warehouseShop = stage_w_sklepRepository.findByNumerSklepuAndTimestampToIsNull(newShop.getNumerSklepu());
                //2 2* b)
                warehouseShop.setTimestampTo(new Timestamp(System.currentTimeMillis()));
                stage_w_sklepRepository.save(warehouseShop);
            }
        }

    }


    private Long getWarehouseAddressId(Long sourceAddressId) {
        SourceToStageIdMap sourceAddressMap = sourceToStageIdMapRepository.
                findBySourceIdAndSourceTableName(sourceAddressId, "ZrodloSystem_Adres");

        return stageToWarehouseIdMapRepository.
                findByStageIdAndStageTableName(sourceAddressMap.getStageId(), sourceAddressMap.getStageTableName()).
                getWarehouseId();
    }

}
