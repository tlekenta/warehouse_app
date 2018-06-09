package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Stage_Dostawa {

    @Id
    private Long id;

    private Integer lp;

    @Column(length = 100)
    private String numerFaktury;

    @Column(length = 100)
    private String dostawca;

    @Column(length = 100)
    private String produkt;

    private Integer liczba;

    private Float cenaJednostkowa;

    private Integer vat;

    private Float kwota;

    private Timestamp dataDostawy;

    private Timestamp dataZaplaty;

    private Timestamp dataImportu;

}
