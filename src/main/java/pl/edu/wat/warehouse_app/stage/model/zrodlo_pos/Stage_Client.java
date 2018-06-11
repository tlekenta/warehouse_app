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
public class Stage_Client implements IStageEntity, IBusinessEntity {

    @Id
    private Long id;

    private String cardnumber;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

    @Override public List getBusinessKey() {
        return Collections.singletonList(cardnumber);
    }
}
