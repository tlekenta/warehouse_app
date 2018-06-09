package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Adres;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class Stage_W_Klient {

    @Id
    private Long klientId;

    private String imie;

    private String nazwisko;

    private String email;

    private String numerTelefonu;

    private Long adresId;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
