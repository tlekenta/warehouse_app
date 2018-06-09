package pl.edu.wat.warehouse_app.zrodlo_system.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
public class ZrodloSystem_ObecnoscWPracy {

    @Id
    private Long Id;

    @ManyToOne
    private ZrodloSystem_Pracownik pracownikId;

    @ManyToOne
    private ZrodloSystem_Sklep sklepId;

    private LocalDateTime przybycie;

    private LocalDateTime wyjscie;

}
