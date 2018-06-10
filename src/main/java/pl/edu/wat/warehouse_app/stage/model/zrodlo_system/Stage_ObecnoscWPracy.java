package pl.edu.wat.warehouse_app.stage.model.zrodlo_system;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class Stage_ObecnoscWPracy implements IStageEntity {

    @Id
    private Long Id;

    private Long pracownikId;

    private Long sklepId;

    private Timestamp przybycie;

    private Timestamp wyjscie;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
