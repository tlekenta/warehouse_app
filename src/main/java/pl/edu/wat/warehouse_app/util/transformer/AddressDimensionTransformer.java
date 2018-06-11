package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Adres;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.Stage_Adres;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_AdresRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.Stage_AdresRepository;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressDimensionTransformer {

    private static final Timestamp OST_IMPORT = new Timestamp(System.currentTimeMillis() - 100000);

    Stage_AdresRepository stage_adresRepository;

    Stage_W_AdresRepository stage_w_adresRepository;

    ReflectionUtils reflectionUtils;

    public void transform() throws IllegalAccessException {
        List<Stage_Adres> sourceAddresses = stage_adresRepository.findAll();

        List<Stage_Adres> newAddresses =
                sourceAddresses
                        .stream()
                        .filter(address -> (address.getTimestampFrom().after(OST_IMPORT) || address.getTimestampTo().after(OST_IMPORT)))
                        .collect(Collectors.toList());

        for(Stage_Adres newAddress: newAddresses) {
            Stage_W_Adres addressToSave = new Stage_W_Adres();

            reflectionUtils.transformFields(newAddress, addressToSave);

            addressToSave.setTimestampFrom(newAddress.getTimestampFrom());
            addressToSave.setTimestampTo(newAddress.getTimestampTo());

            stage_w_adresRepository.save(addressToSave);
        }

    }

}
