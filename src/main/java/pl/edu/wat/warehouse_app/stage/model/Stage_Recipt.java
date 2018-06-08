package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.mapped_clesses.ZrodloPos_Recipt;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
public class Stage_Recipt extends ZrodloPos_Recipt implements IStageEntity {

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
