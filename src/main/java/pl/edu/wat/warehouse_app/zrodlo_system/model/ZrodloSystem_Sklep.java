package pl.edu.wat.warehouse_app.zrodlo_system.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ZrodloSystem_Sklep {

    @Id
    private Long Id;

    @Column(length = 10)
    private String NumerSklepu;

    @Column(length = 50)
    private String Nazwa;

    @Column(length = 101)
    private String Telefon;

    private Long AdresID;
}
