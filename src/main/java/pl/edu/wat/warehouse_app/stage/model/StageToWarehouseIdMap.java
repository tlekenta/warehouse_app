package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class StageToWarehouseIdMap {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    Long stageId;

    Long warehouseId;

    String stageTableName;

    String warehouseTableName;


}
