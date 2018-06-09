package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Adres;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
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

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
