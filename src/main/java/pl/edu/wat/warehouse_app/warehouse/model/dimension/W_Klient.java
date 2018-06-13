package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class W_Klient {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long klientId;

    @Column(length = 10)
    private String numerKlienta;

    private String imie;

    private String nazwisko;

    private String numerTelefonu;

    private Long adresId;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
