package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class TMP_F_Sprzedaz {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    //klucz biznesowy
    private String numerParagonu;

    //klucz biznesowy
    private Integer pozycjaPragonu;

    private Long produktId;

    private Long pracownikId;

    private Long czasId;

    private Long sklepId;

    private Integer liczbaSztuk;

    private Float cenaJednostkowa;

    private Integer stawkaVat;

    private Float cenaBrutto;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
