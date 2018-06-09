package pl.edu.wat.warehouse_app.stage.model.zrodlo_pos;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
public class Stage_Receipt implements IStageEntity {

    @Id
    private Long id;

    private Long clientId;

    private Long userId;

    private Timestamp date;

    private double totalCost;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
