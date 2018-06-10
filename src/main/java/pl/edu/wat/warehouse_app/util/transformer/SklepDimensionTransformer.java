package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Sklep;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_Adres;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_Sklep;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_SklepRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_SklepRepository;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class SklepDimensionTransformer {

    Stage_SklepRepository stage_sklepRepository;

    Stage_W_SklepRepository stage_w_sklepRepository;

    Stage_AdresRepository stage_adresRepository;

    Stage_W_AdresRepository stage_w_adresRepository;

    ReflectionUtils reflectionUtils;

    public void transform() throws IllegalAccessException {
        List<Stage_Sklep> sourceShops = stage_sklepRepository.findAll();

        for(Stage_Sklep sourceShop: sourceShops) {
            Stage_W_Sklep warehouseShop = stage_w_sklepRepository.findByNumerSklepu(sourceShop.getNumerSklepu());
            Stage_Adres sourceAddress = stage_adresRepository.findOne(sourceShop.getAdresID());
            Long warehouseAddressId = stage_w_adresRepository.findByUlicaAndNumerDomuAndNumerMieszkaniaAndKodPocztowyAndMiastoAndPoczta(
                    sourceAddress.getUlica(),
                    sourceAddress.getNumerBudynku(),
                    sourceAddress.getNumerLokalu(),
                    sourceAddress.getKodPocztowy(),
                    sourceAddress.getMiasto(),
                    sourceAddress.getPoczta()
            ).getAdresId();

            //TODO: dodać obsługę błędu jak brak adresu

            if(warehouseShop == null) {
                warehouseShop = new Stage_W_Sklep();

                reflectionUtils.transformFields(sourceShop, warehouseShop);

                warehouseShop.setAdresId(warehouseAddressId);

                warehouseShop.setImportTime(new Timestamp(System.currentTimeMillis()));
                warehouseShop.setCreationTime(new Timestamp(System.currentTimeMillis()));

                stage_w_sklepRepository.save(warehouseShop);

            } else {
                if(reflectionUtils.compareAndRewriteFields(sourceShop, warehouseShop)) {
                    warehouseShop.setImportTime(new Timestamp(System.currentTimeMillis()));
                    stage_w_sklepRepository.save(warehouseShop);
                }

                if(!warehouseShop.getAdresId().equals(warehouseAddressId)) {
                    warehouseShop.setAdresId(warehouseAddressId);
                    warehouseShop.setImportTime(new Timestamp(System.currentTimeMillis()));
                    stage_w_sklepRepository.save(warehouseShop);
                }
            }
        }

    }

}
