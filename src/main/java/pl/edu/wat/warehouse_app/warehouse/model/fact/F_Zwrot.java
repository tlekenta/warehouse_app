package pl.edu.wat.warehouse_app.warehouse.model.fact;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class F_Zwrot {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    //klucz biznesowy
    private String nrZwrotu;

    //klucz biznesowy
    private String numerParagonu;

    //klucz biznesowy
    private Integer pozycjaParagonu;

    private Long produktId;

    private Long klientId;

    private Long dataId;

    private Long sklepId;

    private Integer liczbaSztuk;

    private Float cenaJednostkowa;

    private Float strataCalkowita;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
