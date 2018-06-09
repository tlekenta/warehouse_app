package pl.edu.wat.warehouse_app.stage.model.zrodlo_pos;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Stage_User implements IStageEntity {

    @Id
    private Long id;

    private String userNumber;

    private Timestamp creationTime;

    private Timestamp importTime;

}
