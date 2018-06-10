package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class Stage_W_Pracownik {

    @Id
    @GeneratedValue
    private Long pracownikId;

    @TransformedField(name = "prac_nr")
    private String numerPracownika;

    @TransformedField(name = "prac_imie")
    private String imie;

    @TransformedField(name = "prac_nazwisko")
    private String nazwisko;

    private Long adresId;

    @Column(length = 11)
    @TransformedField(name = "prac_pesel")
    private String pesel;

    @TransformedField(name = "prac_tel")
    private String telefon;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
