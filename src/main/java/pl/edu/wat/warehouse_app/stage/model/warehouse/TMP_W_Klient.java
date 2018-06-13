package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TMP_W_Klient {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long klientId;

    @Column(length = 10)
    @TransformedField(name = "kli_nr")
    private String numerKlienta;

    @TransformedField(name = "kli_imie")
    private String imie;

    @TransformedField(name = "kli_nazwisko")
    private String nazwisko;

    @TransformedField(name = "kli_tel")
    private String numerTelefonu;

    private Long adresId;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
