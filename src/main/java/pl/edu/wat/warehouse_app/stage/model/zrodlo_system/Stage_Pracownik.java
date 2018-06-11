package pl.edu.wat.warehouse_app.stage.model.zrodlo_system;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Entity
@Data
public class Stage_Pracownik implements IStageEntity, IBusinessEntity {

    @Id
    private Long Id;

    @Column(length = 10)
    @TransformedField(name = "prac_nr")
    private String NumerPracownika;

    @Column(length = 11)
    @TransformedField(name = "prac_pesel")
    private String Pesel;

    @Column(length = 100)
    @TransformedField(name = "prac_tel")
    private String Telefon;

    private Long TypPracownikaId;

    private Long AdresID;

    @Column(length = 50)
    @TransformedField(name = "prac_imie")
    private String Imie;

    @Column(length = 50)
    @TransformedField(name = "prac_nazwisko")
    private String Nazwisko;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

    @Override public List getBusinessKey() {
        return Collections.singletonList(NumerPracownika);
    }
}
