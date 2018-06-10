package pl.edu.wat.warehouse_app.zrodlo_system.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class ZrodloSystem_ObecnoscWPracy {

    @Id
    private Long Id;

    private Long pracownikId;

    private Long sklepId;

    private Timestamp przybycie;

    private Timestamp wyjscie;

}
