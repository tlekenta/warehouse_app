package pl.edu.wat.warehouse_app.stage.model;

import java.sql.Timestamp;

public interface IStageEntity {

    void setCreationTime(Timestamp pDate);

    void setImportTime(Timestamp pDate);

}
