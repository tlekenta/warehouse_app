package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.mapped_clesses.ZrodloPos_User;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
public class Stage_User extends ZrodloPos_User implements IStageEntity {

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
