package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.SourceToStageIdMap;
import pl.edu.wat.warehouse_app.stage.model.StageToWarehouseIdMap;
import pl.edu.wat.warehouse_app.stage.model.warehouse.*;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_pos.Stage_Receipt;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_pos.Stage_ReceiptItem;
import pl.edu.wat.warehouse_app.stage.repository.SourceToStageIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.StageToWarehouseIdMapRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.*;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_pos.Stage_ProductRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_pos.Stage_ReceiptItemRepository;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_pos.Stage_ReceiptRepository;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SprzedazFactTransformer {

    /*
    1. Pobrać wszystkie nowe paragony
    2. Dla każdego nowego paragonu pobrać jego elmenty (nie trzeba sprawdzać czy elemnty są nowe bo paragon jest nowy)
        * w Stage_ReciptItem reciptId jest ustawione na złe id -> skorzystać z mapowania source do stage
    3. Dla każdego paragonu pobrać id pracownika i id sklepu
        * Skorzystać z mapowania source do stage i potem z stage do warehouse (będę wtedy miał idki z tabel Stage_W_*)
    3. Dla każdego elementu paragonu stworzyć fakt sprzedaży
    */

    Stage_W_ProductRepository stage_w_productRepository;

    Stage_W_SklepRepository stage_w_sklepRepository;

    Stage_W_PracownikRepository stage_w_pracownikRepository;

    Stage_ReceiptRepository stage_receiptRepository;

    Stage_ReceiptItemRepository stage_receiptItemRepository;

    Stage_ProductRepository stage_productRepository;

    Stage_F_Zmiana_PracowniczaRepository stage_f_zmiana_pracowniczaRepository;

    Stage_F_SprzedazRepository stage_f_sprzedazRepository;

    ReflectionUtils reflectionUtils;

    DbLogger logger;

    SourceToStageIdMapRepository sourceToStageIdMapRepository;

    StageToWarehouseIdMapRepository stageToWarehouseIdMapRepository;

    Stage_W_CzasRepository stage_w_czasRepository;

    public void transform() throws IllegalAccessException {

        Timestamp lastImport = logger.getLastImportTimestamp(Stage_F_Sprzedaz.class.getSimpleName());

        List<Stage_Receipt> stageReceipts = stage_receiptRepository.findAll();

        List<Stage_Receipt> newRecipts =
                stageReceipts.
                        stream().
                        //nie biorę pod uwagę usuwania/zmian
                        filter(recipt -> recipt.getTimestampFrom().after(lastImport)).
                        collect(Collectors.toList());

        for(Stage_Receipt newRecipt: newRecipts) {
            Long workerId = getWarehouseWorkerId(newRecipt.getUserId());

            newRecipt.getDate().setSeconds(0);
            Stage_W_Czas czasZakupu = stage_w_czasRepository.findByDateTime(newRecipt.getDate());

            Long shopId = getShopId(czasZakupu, workerId);

            List<Stage_ReceiptItem> receiptItems = stage_receiptItemRepository.findByReciptNumber(newRecipt.getReciptNumber());

            for(Stage_ReceiptItem receiptItem: receiptItems) {
                Stage_W_Produkt produkt = getProdukt(receiptItem.getProductId(), newRecipt, czasZakupu);

                Stage_F_Sprzedaz sprzedaz = new Stage_F_Sprzedaz();

                if(produkt != null) {
                    sprzedaz.setCenaJednostkowa(produkt.getCenaJednostkowa());
                    sprzedaz.setProduktId(produkt.getProduktId());
                }

                sprzedaz.setCzasId(czasZakupu.getCzasId());
                sprzedaz.setNumerParagonu(receiptItem.getReciptNumber());
                sprzedaz.setPozycjaPragonu(receiptItem.getPosition());

                sprzedaz.setPracownikId(workerId);

                sprzedaz.setSklepId(shopId);

                sprzedaz.setTimestampFrom(receiptItem.getTimestampFrom());

                stage_f_sprzedazRepository.save(sprzedaz);
            }


        }
    }

    private Long getShopId(Stage_W_Czas czasZakupu, Long workerId) {
        //chcę znaleźć sklep po dacie zakupu i id pracownika
        /*
        1. Wyciągnąć wzystkie zmiany dla czasu zakupu
        2. Znaleźć zmianę z szukanym pracownikiem
        3. Jeżeli nie ma to null
         */

        List<Stage_F_Zmiana_Pracownicza> zmianyPracownicze = stage_f_zmiana_pracowniczaRepository.findAll();

        List<Stage_F_Zmiana_Pracownicza> przefiltrowaneZmiany = new LinkedList<>();

        for(Stage_F_Zmiana_Pracownicza zmiana: zmianyPracownicze) {
            Stage_W_Czas poczatekZmiany = stage_w_czasRepository.getOne(zmiana.getDataRozpoczeciaId());
            Stage_W_Czas koniecZmiany = stage_w_czasRepository.getOne(zmiana.getDataZakonczeniaId());

        }

        return (przefiltrowaneZmiany.size() == 1) ? przefiltrowaneZmiany.get(0).getSklepId() : null;
    }

    private Long getWarehouseWorkerId(Long sourceWorkerId) {
        SourceToStageIdMap sourceWorkerMap = sourceToStageIdMapRepository.
                findBySourceIdAndSourceTableName(sourceWorkerId, "ZrodloSystem_Pracownik");

        return stageToWarehouseIdMapRepository.
                findByStageIdAndStageTableName(sourceWorkerMap.getStageId(), sourceWorkerMap.getStageTableName()).
                getWarehouseId();
    }

    private Stage_W_Produkt getProdukt(Long sourceProductId, Stage_Receipt receipt, Stage_W_Czas czasZakupu) {
        SourceToStageIdMap sourceProductMap = sourceToStageIdMapRepository.
                findBySourceIdAndSourceTableName(sourceProductId, "ZrodloPos_Product");

        String barcode = stage_productRepository.findById(sourceProductMap.getStageId()).getBarcode();

        //chcę wyciągnąć produkt, który obowiązywał w momencie sprzedazy lub najbardziej aktualny jeżeli takiego nie ma
        List<Stage_W_Produkt> w_produkts = stage_w_productRepository.findByKodKreskowy(barcode);

        List<Stage_W_Produkt> filtredProducts = w_produkts
                .stream()
                .filter(produkt -> czasZakupu.getDateTime().after(produkt.getTimestampFrom()) && czasZakupu.getDateTime().before(produkt.getTimestampTo()))
                .collect(Collectors.toList());

        if(filtredProducts.size() == 1) {
            return filtredProducts.get(0);
        } else {
            return stage_w_productRepository.findByKodKreskowyAndTimestampToIsNull(barcode);
        }

    }

}
