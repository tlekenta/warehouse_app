package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Stage_Unit implements IStageEntity {

    @Id
    private Long id;

    private String unitName;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
