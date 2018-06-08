package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;
import pl.edu.wat.warehouse_app.zrodlo_pos.model.ZrodloPos_Client;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
public class Stage_Client extends ZrodloPos_Client {

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
