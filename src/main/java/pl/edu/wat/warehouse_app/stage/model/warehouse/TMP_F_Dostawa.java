package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class TMP_F_Dostawa {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    //klucz biznesowy
    @TransformedField(name = "numer_dokumentu")
    private String nrDokumentuDostawy;

    //klucz biznesowy
    @TransformedField(name = "pozycja_dokumentu")
    private Integer pozycjaDokumentu;

    private Long produktId;

    private Long dataDostawyId;

    private Long dataZaplatyId;

    private Long sklepId;

    @TransformedField(name = "dostawca")
    private String dostawca;

    @TransformedField(name = "sztuki")
    private Integer liczbaSztuk;

    @TransformedField(name = "cena_jedn")
    private Float cenaJednostkowa;

    @TransformedField(name = "cena_brutto")
    private Float cenaBrutto;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
