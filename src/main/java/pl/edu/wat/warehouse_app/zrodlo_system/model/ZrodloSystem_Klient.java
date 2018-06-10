package pl.edu.wat.warehouse_app.zrodlo_system.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ZrodloSystem_Klient {

    @Id
    private Long Id;

    @Column(length = 10)
    private String NumerKlienta;

    @Column(length = 100)
    private String Telefon;

    private Long AdresId;

    @Column(length = 50)
    private String Imie;

    @Column(length = 50)
    private String Nazwisko;

}
