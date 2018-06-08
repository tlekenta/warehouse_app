package pl.edu.wat.warehouse_app.stage.model;

import java.time.LocalDateTime;

public interface IStageEntity {

    void setCreationTime(LocalDateTime pDate);

    void setImportTime(LocalDateTime pDate);

}
