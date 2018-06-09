package pl.edu.wat.warehouse_app.zrodlo_system.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
@Data
public class ZrodloSystem_ObecnoscWPracy {

    @Id
    private Long Id;

    @ManyToOne
    private ZrodloSystem_Pracownik pracownikId;

    @ManyToOne
    private ZrodloSystem_Sklep sklepId;

    private Timestamp przybycie;

    private Timestamp wyjscie;

}
