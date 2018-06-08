package pl.edu.wat.warehouse_app.stage.model;

import pl.edu.wat.warehouse_app.zrodlo_pos.model.ZrodloPos_Client;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Stage_Client extends ZrodloPos_Client {

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
