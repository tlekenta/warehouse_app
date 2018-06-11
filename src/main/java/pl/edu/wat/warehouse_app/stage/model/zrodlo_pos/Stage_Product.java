package pl.edu.wat.warehouse_app.stage.model.zrodlo_pos;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Entity
@Data
public class Stage_Product implements IStageEntity, IBusinessEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long unitId;

    @TransformedField(name = "prod_nazwa")
    private String name;

    @TransformedField(name = "prod_cena")
    private double value;

    @TransformedField(name = "prod_kod")
    private String barcode;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

    @Override public List getBusinessKey() {
        return Collections.singletonList(barcode);
    }
}
