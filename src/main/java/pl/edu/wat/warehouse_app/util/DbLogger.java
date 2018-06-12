package pl.edu.wat.warehouse_app.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.metadata.model.*;
import pl.edu.wat.warehouse_app.metadata.repository.LogImportRepository;
import pl.edu.wat.warehouse_app.metadata.repository.LogRepository;
import pl.edu.wat.warehouse_app.metadata.repository.LogStageRepository;
import pl.edu.wat.warehouse_app.metadata.repository.LogWarehouseRepository;
import pl.edu.wat.warehouse_app.stage.model.Stage_Dostawa;
import pl.edu.wat.warehouse_app.stage.model.Stage_Promocja;
import pl.edu.wat.warehouse_app.stage.model.warehouse.*;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_pos.*;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.*;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.*;
import pl.edu.wat.warehouse_app.warehouse.model.fact.*;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class DbLogger {

    RepositoryFactory repositoryFactory;

    LogRepository logRepository;
    LogImportRepository logImportRepository;
    LogWarehouseRepository logWarehouseRepository;
    LogStageRepository logStageRepository;

    public void log(String message, Class entity, Class throwingClass) {
        saveMessage(message, entity, LogType.INFO, throwingClass);
    }

    public void error(String message, Class entity, Class throwingClass) {
        saveMessage(message, entity, LogType.ERROR, throwingClass);
    }

    private void saveMessage(String message, Class entity, LogType logType, Class throwingClass) {
        LogTable log = new LogTable(
                null,
                message,
                entity.getSimpleName(),
                throwingClass.getSimpleName(),
                new Timestamp(System.currentTimeMillis()),
                logType
        );

        logRepository.save(log);

    }

    public void logWarehouse(){
        Class[] warehouseTables = {
                W_Adres.class,
                W_Czas.class,
                W_Data.class,
                W_Klient.class,
                W_Pracownik.class,
                W_Produkt.class,
                W_Sklep.class,
                F_Dostawa.class,
                F_Promocja.class,
                F_Sprzedaz.class,
                F_Zmiana_Pracownicza.class,
                F_Zwrot.class
        };
        for (Class warehouseTable:warehouseTables) {
            LogWarehouse log = new LogWarehouse(
                    null,
                    warehouseTable.getSimpleName(),
                    getLastImportTimestamp(warehouseTable.getSimpleName()),
                    repositoryFactory.getWarehouseRepository(warehouseTable).findAll().size()
            );
            logWarehouseRepository.save(log);
        }
    }

    public void logStage(){
        Class[] stageTables = {
                Stage_Client.class,
                Stage_Product.class,
                Stage_ReceiptItem.class,
                Stage_Receipt.class,
                Stage_Unit.class,
                Stage_User.class,
                Stage_Adres.class,
                Stage_Klient.class,
                Stage_ObecnoscWPracy.class,
                Stage_Pracownik.class,
                Stage_Sklep.class,
                Stage_TypPracownika.class,
                Stage_Dostawa.class,
                Stage_Promocja.class,
                Stage_W_Adres.class,
                Stage_W_Czas.class,
                Stage_W_Data.class,
                Stage_W_Klient.class,
                Stage_W_Pracownik.class,
                Stage_W_Produkt.class,
                Stage_W_Sklep.class,
                Stage_F_Dostawa.class,
                Stage_F_Promocja.class,
                Stage_F_Sprzedaz.class,
                Stage_F_Zmiana_Pracownicza.class,
                Stage_F_Zwrot.class
        };
        for (Class stageTable:stageTables) {
            LogStage log = new LogStage(
                    null,
                    stageTable.getSimpleName(),
                    getLastImportTimestamp(stageTable.getSimpleName()),
                    repositoryFactory.getStageRepositoryByStageClass(stageTable).findAll().size()
            );
            logStageRepository.save(log);
        }
    }

    public Timestamp getLastImportTimestamp(String className){
        LogImport logImport = logImportRepository.findTopByTableNameAndSuccessIsTrue(className);
        return (null== logImport)? new Timestamp(System.currentTimeMillis() - 100000) : logImport.getImportTime();
    }

    public void logImport(String tableName, Timestamp importTimestamp, Boolean success) {
        LogImport logImport = new LogImport(
                null,
                tableName,
                success,
                importTimestamp
        );

        logImportRepository.save(logImport);
    }

}
