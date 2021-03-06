package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
public class TMP_F_Zwrot implements IBusinessEntity, IStageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @TransformedField(name = "kod_paragonu")
    private String receiptCode;

    private Long productId;

    private Long klientId;

    @TransformedField(name = "data_zawrotu")
    private Timestamp date;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;


    @Override
    @Transient
    public List getBusinessKey() {
        return Arrays.asList(receiptCode, productId);
    }
}
