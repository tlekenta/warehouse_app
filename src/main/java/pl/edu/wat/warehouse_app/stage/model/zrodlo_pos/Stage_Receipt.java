package pl.edu.wat.warehouse_app.stage.model.zrodlo_pos;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Entity
@Data
public class Stage_Receipt implements IStageEntity, IBusinessEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String reciptNumber;

    private Long clientId;

    private Long userId;

    private Timestamp date;

    private double totalCost;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

    @Override public List getBusinessKey() {
        return Collections.singletonList(reciptNumber);
    }
}
