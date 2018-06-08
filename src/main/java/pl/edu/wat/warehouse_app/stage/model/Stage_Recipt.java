package pl.edu.wat.warehouse_app.stage.model;

import pl.edu.wat.warehouse_app.zrodlo_pos.model.ZrodloPos_Recipt;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
public class Stage_Recipt extends ZrodloPos_Recipt {

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
