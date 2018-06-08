package pl.edu.wat.warehouse_app.stage.model;

import pl.edu.wat.warehouse_app.zrodlo_pos.model.ZrodloPos_ReciptItem;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
public class Stage_ReciptItem extends ZrodloPos_ReciptItem {

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
