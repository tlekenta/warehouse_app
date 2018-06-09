package pl.edu.wat.warehouse_app.zrodlo_system.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class ZrodloSystem_Pracownik {

    @Id
    private Long Id;

    @Column(length = 10)
    private String NumerPracownika;

    @Column(length = 11)
    private String Pesel;

    @Column(length = 100)
    private String Telefon;

    @ManyToOne
    private ZrodloSystem_TypPracownika TypPracownikaId;

    @ManyToOne
    private ZrodloSystem_Adres AdresID;


    @Column(length = 50)
    private String Imie;

    @Column(length = 50)
    private String Nazwisko;

}
