package pl.edu.wat.warehouse_app.stage.model.zrodlo_pos;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class Stage_Zwrot implements IStageEntity, IBusinessEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String kodKreskowy;
    private String klientNumber;

    @TransformedField(name = "kod_paragonu")
    private String receiptCode;
    @TransformedField(name = "data_zawrotu")
    private Timestamp date;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

    @Override
    public Long getId() { return id; }

    @Override
    @Transient
    public List getBusinessKey() {
        return Arrays.asList(receiptCode, kodKreskowy);
    }
}
