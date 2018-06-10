package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Stage_W_Klient {

    @Id
    @GeneratedValue
    private Long klientId;

    @Column(length = 10)
    private String numerKlienta;

    private String imie;

    private String nazwisko;

    private String numerTelefonu;

    private Long adresId;

    private Timestamp creationTime;

    private Timestamp importTime;

}
