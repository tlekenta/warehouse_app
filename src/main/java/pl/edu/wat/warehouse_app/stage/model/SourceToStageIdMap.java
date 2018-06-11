package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class SourceToStageIdMap {

    @Id
    @GeneratedValue
    Long id;

    Long sourceId;

    Long stageId;

    String sourceTableName;

    String stageTableName;

}
