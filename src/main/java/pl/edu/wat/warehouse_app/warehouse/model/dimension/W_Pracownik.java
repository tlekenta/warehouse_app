package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class W_Pracownik {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String numerPracownika;

    private String imie;

    private String nazwisko;

    private Long adresId;

    @Column(length = 11)
    private String pesel;

    private String telefon;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;
}
