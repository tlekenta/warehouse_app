package pl.edu.wat.warehouse_app.warehouse.model.fact;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class F_Dostawa {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    //klucz biznesowy
    private String nrDokumentuDostawy;

    //klucz biznesowy
    private Integer pozycjaDokumentu;

    private Long produktId;

    private Long dataDostawyId;

    private Long dataZaplatyId;

    private Long sklepId;

    private String dostawca;

    private Integer liczbaSztuk;

    private Float cenaJednostkowa;

    private Float cenaBrutto;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;
}
