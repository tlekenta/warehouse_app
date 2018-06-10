package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Pracownik;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_Adres;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_Pracownik;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_PracownikRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_PracownikRepository;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.List;

@Service
@AllArgsConstructor
public class PracownikDimensionTransformer {

    Stage_PracownikRepository stage_pracownikRepository;

    Stage_W_PracownikRepository stage_w_pracownikRepository;

    Stage_AdresRepository stage_adresRepository;

    Stage_W_AdresRepository stage_w_adresRepository;

    ReflectionUtils reflectionUtils;

    DbLogger logger;

    public void transform() throws IllegalAccessException {
        List<Stage_Pracownik> sourceWorkers = stage_pracownikRepository.findAll();

        for(Stage_Pracownik sourceWorker: sourceWorkers) {
            Stage_W_Pracownik warehouseWorker = stage_w_pracownikRepository.findByPesel(sourceWorker.getPesel());
            Stage_Adres sourceAddress = stage_adresRepository.findOne(sourceWorker.getAdresID());
            Long warehouseAddressId = stage_w_adresRepository.findByUlicaAndNumerDomuAndNumerMieszkaniaAndKodPocztowyAndMiastoAndPoczta(
                sourceAddress.getUlica(),
                sourceAddress.getNumerBudynku(),
                sourceAddress.getNumerLokalu(),
                sourceAddress.getKodPocztowy(),
                sourceAddress.getMiasto(),
                sourceAddress.getPoczta()
            ).getAdresId();

            if(warehouseAddressId == null) {
                logger.error(MessageFormat.format("W hurtowni nie istnieje adres: ul. {0} {1} m. {2}",
                        sourceAddress.getUlica(),
                        sourceAddress.getNumerBudynku(),
                        sourceAddress.getNumerLokalu()), sourceAddress.getClass(), this.getClass());
            }

            if(warehouseWorker == null) {
                warehouseWorker = new Stage_W_Pracownik();

                reflectionUtils.transformFields(sourceWorker, warehouseWorker);

                warehouseWorker.setAdresId(warehouseAddressId);

                warehouseWorker.setTimestampTo(new Timestamp(System.currentTimeMillis()));
                warehouseWorker.setTimestampFrom(new Timestamp(System.currentTimeMillis()));

                stage_w_pracownikRepository.save(warehouseWorker);

            } else {
                if(reflectionUtils.compareAndRewriteFields(sourceWorker, warehouseWorker)) {
                    warehouseWorker.setTimestampTo(new Timestamp(System.currentTimeMillis()));
                    stage_w_pracownikRepository.save(warehouseWorker);
                }

                if(warehouseWorker.getAdresId() != null && !warehouseWorker.getAdresId().equals(warehouseAddressId)) {
                    warehouseWorker.setAdresId(warehouseAddressId);
                    warehouseWorker.setTimestampTo(new Timestamp(System.currentTimeMillis()));
                    stage_w_pracownikRepository.save(warehouseWorker);
                }
            }

        }

    }

}
