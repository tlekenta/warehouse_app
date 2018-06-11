package pl.edu.wat.warehouse_app.stage.model;

import java.sql.Timestamp;

public interface IStageEntity {

    void setTimestampFrom(Timestamp pDate);

    void setTimestampTo(Timestamp pDate);

    Timestamp getTimestampFrom();

    Timestamp getTimestampTo();

}
