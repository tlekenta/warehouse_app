package pl.edu.wat.warehouse_app.stage.model.zrodlo_system;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TmpToWarehouseIdMap {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    Long tmpId;

    String tmpTableName;

    Long warehouseId;

    String warehouseTableName;

}
