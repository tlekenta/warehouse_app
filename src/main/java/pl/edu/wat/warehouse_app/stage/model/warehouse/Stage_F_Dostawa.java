package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class Stage_F_Dostawa {

    @Id
    @GeneratedValue
    private Long id;

    //klucz biznesowy
    private String nrDokumentuDostawy;

    //klucz biznesowy
    private Integer pozycjaDokumentu;

    private Long produktId;

    private Long dostawcaId;

    private Long dataId;

    private Long sklepId;

    private Integer liczbaSztuk;

    private Float cenaJednostkowa;

    private Integer stawkaVat;

    private Float cenaBrutto;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
