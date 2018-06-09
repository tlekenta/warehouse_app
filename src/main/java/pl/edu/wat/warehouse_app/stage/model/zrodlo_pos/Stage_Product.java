package pl.edu.wat.warehouse_app.stage.model.zrodlo_pos;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Stage_Product implements IStageEntity {

    @Id
    private Long id;

    private Long unitId;

    private String name;

    private double value;

    private String barcode;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
