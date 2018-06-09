package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Adres;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class Stage_W_Pracownik {

    @Id
    Long pracownikId;

    Integer numerPracownika;

    String imie;

    String nazwisko;

    @ManyToOne
    W_Adres adresId;

    @Column(length = 11)
    String pesel;

    String telefon;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
