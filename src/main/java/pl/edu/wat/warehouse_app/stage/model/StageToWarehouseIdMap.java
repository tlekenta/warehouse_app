package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class StageToWarehouseIdMap {

    @Id
    @GeneratedValue
    Long id;

    Long stageId;

    Long warehouseId;

    String stageTableName;

    String warehouseTableName;


}
