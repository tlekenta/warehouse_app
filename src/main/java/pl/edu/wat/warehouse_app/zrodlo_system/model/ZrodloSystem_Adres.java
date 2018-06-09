package pl.edu.wat.warehouse_app.zrodlo_system.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ZrodloSystem_Adres {

    @Id
    private Long id;

    @Column(length = 100)
    private String ulica;

    private Integer numerBudynku;

    private Integer numerLokalu;
}
