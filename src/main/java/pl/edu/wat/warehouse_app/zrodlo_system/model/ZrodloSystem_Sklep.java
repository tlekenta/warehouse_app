package pl.edu.wat.warehouse_app.zrodlo_system.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

    @ManyToOne
    private ZrodloSystem_Adres AdresID;
}
