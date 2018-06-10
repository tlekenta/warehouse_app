package pl.edu.wat.warehouse_app.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.metadata.model.LogTable;
import pl.edu.wat.warehouse_app.metadata.model.LogType;
import pl.edu.wat.warehouse_app.metadata.repository.LogRepository;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class DbLogger {

    LogRepository logRepository;

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

}
