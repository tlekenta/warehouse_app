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
    private Long pracownikId;

    private Integer numerPracownika;

    private String imie;

    private String nazwisko;

    private Long adresId;

    @Column(length = 11)
    private String pesel;

    private String telefon;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
