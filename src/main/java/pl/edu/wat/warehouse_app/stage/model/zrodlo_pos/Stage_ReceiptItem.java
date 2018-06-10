package pl.edu.wat.warehouse_app.stage.model.zrodlo_pos;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class Stage_ReceiptItem implements IStageEntity {

    @Id
    private Long id;

    private Long receiptId;

    private Long productId;

    private double amount;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
