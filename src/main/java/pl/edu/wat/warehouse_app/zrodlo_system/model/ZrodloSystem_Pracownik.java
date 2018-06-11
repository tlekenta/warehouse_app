package pl.edu.wat.warehouse_app.zrodlo_system.model;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Data
public class ZrodloSystem_Pracownik implements IBusinessEntity {

    @Id
    private Long Id;

    @Column(length = 10)
    private String NumerPracownika;

    @Column(length = 11)
    private String Pesel;

    @Column(length = 100)
    private String Telefon;

    private Long TypPracownikaId;

    private Long AdresID;


    @Column(length = 50)
    private String Imie;

    @Column(length = 50)
    private String Nazwisko;

    @Override
    @Transient
    public List getBusinessKey() {
        return Collections.singletonList(NumerPracownika);
    }
}
