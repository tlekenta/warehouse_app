package pl.edu.wat.warehouse_app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_W_Czas;
import pl.edu.wat.warehouse_app.stage.model.warehouse.TMP_W_Data;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_CzasRepository;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.TMP_W_DataRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Service
public class DictionaryGenerator {

    @Autowired
    TMP_W_DataRepository dataRepository;
    @Autowired
    TMP_W_CzasRepository czasRepository;

    public void generateDateAndTimes() {

        LocalDateTime firstDate = LocalDateTime.of(2018, 4, 28, 0, 0);
        LocalDateTime lastDate = LocalDateTime.of(2018, 5, 3, 0, 0);

        Boolean flag = true;

        while (flag) {
            int miesiac = firstDate.getMonth().getValue();
            int rok = firstDate.getYear();
            int dzien = firstDate.getDayOfMonth();
            Date date = new Date(rok - 1900, miesiac - 1, dzien);
            int kwartal = 4;
            if (miesiac < 4) {
                kwartal = 1;
            } else if (miesiac < 7) {
                kwartal = 2;
            } else if (miesiac < 10) {
                kwartal = 3;
            }
            TMP_W_Data dataDimension = createDate(date, rok, kwartal, miesiac, dzien);

            createTimeForDay(dataDimension);

            firstDate = firstDate.plusDays(1);

            if (firstDate.isAfter(lastDate)) {
                flag = false;
            }
        }
    }

    private void createTimeForDay(TMP_W_Data dataDimension) {
        for (int godz = 0; godz < 24; godz++) {
            for (int min = 0; min < 60; min++) {
                    createTime(dataDimension, godz, min);
            }
        }
    }

    private TMP_W_Data createDate(Date date, int rok, int kwartal, int miesiac, int dzien) {
        TMP_W_Data data = new TMP_W_Data();
        data.setData(date);
        data.setKwartal(kwartal);
        data.setDzien(dzien);
        data.setMiesiac(miesiac);
        data.setRok(rok);
        data.setTimestampFrom(new Timestamp(System.currentTimeMillis()));
        dataRepository.save(data);
        return data;
    }

    private TMP_W_Czas createTime(TMP_W_Data stageData, int godzina, int minuta) {
        TMP_W_Czas czas = new TMP_W_Czas();
        czas.setDataId(stageData.getId());
        czas.setGodzina(godzina);
        czas.setMinuta(minuta);
        czas.setDateTime(new Timestamp(stageData.getRok() - 1900, stageData.getMiesiac() - 1, stageData.getDzien(), godzina, minuta, 0, 0));
        czas.setTimestampFrom(new Timestamp(System.currentTimeMillis()));
        czasRepository.save(czas);
        return czas;
    }

    public boolean isDateTimeDictionaryGenerated() {
        return czasRepository.count() == 8640;
    }
}
