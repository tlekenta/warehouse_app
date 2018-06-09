package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Stage_User implements IStageEntity {

    @Id
    private Long id;

    private String userNumber;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
