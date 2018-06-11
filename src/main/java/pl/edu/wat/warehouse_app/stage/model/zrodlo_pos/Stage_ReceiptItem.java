package pl.edu.wat.warehouse_app.stage.model.zrodlo_pos;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class Stage_ReceiptItem implements IStageEntity, IBusinessEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String reciptNumber;

    private Integer position;

    private Long receiptId;

    private Long productId;

    private double amount;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

    @Override public List getBusinessKey() {
        return Arrays.asList(reciptNumber, position);
    }
}
