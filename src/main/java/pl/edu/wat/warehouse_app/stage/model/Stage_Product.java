package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;

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
