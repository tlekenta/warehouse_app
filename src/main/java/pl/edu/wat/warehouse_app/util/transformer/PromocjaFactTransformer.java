package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.Stage_Promocja;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_F_Promocja;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Data;
import pl.edu.wat.warehouse_app.stage.model.warehouse.Stage_W_Produkt;
import pl.edu.wat.warehouse_app.stage.repository.Stage_PromocjaRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_F_PromocjaRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_DataRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.Stage_W_ProductRepository;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class PromocjaFactTransformer {

    ReflectionUtils reflectionUtils;

    Stage_PromocjaRepository stage_promocjaRepository;
    Stage_F_PromocjaRepository stage_f_promocjaRepository;
    Stage_W_DataRepository stage_w_dataRepository;
    Stage_W_ProductRepository stage_w_productRepository;

    DbLogger logger;

    public void transform() throws IllegalAccessException {

        List<Stage_Promocja> sourceSales = stage_promocjaRepository.findAll();

        for(Stage_Promocja sourceSale: sourceSales) {

            Stage_F_Promocja newSale = new Stage_F_Promocja();

            newSale.setTimestampFrom(sourceSale.getTimestampFrom());
            newSale.setProcentObnizki(sourceSale.getProcentPromocji());

            Timestamp saleDataDataPromocjiDo = sourceSale.getDataPromocjiDo();
            Timestamp saleDataDataPromocjiOd = sourceSale.getDataPromocjiOd();

            Stage_W_Data saleDateFrom = stage_w_dataRepository.findByRokAndMiesiacAndDzien(saleDataDataPromocjiOd.getYear() + 1900, saleDataDataPromocjiOd.getMonth()+1, saleDataDataPromocjiOd.getDate());
            Stage_W_Data saleDateTo = stage_w_dataRepository.findByRokAndMiesiacAndDzien(saleDataDataPromocjiDo.getYear() + 1900, saleDataDataPromocjiDo.getMonth()+1, saleDataDataPromocjiDo.getDate());

            newSale.setDataKoncowaId(saleDateTo.getDataId());
            newSale.setDataPoczatkowaId(saleDateFrom.getDataId());

            Stage_W_Produkt produkt = stage_w_productRepository.findByKodKreskowy(sourceSale.getKodKreskowy());

            newSale.setProduktId(produkt.getProduktId());

            stage_f_promocjaRepository.save(newSale);

        }

        logger.logImport(Stage_F_Promocja.class.getSimpleName(),new Timestamp(System.currentTimeMillis()), true);
    }

}

