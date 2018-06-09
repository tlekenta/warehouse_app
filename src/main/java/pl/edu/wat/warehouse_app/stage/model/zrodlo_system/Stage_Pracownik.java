package pl.edu.wat.warehouse_app.stage.model.zrodlo_system;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Stage_Pracownik implements IStageEntity {

    @Id
    private Long Id;

    @Column(length = 10)
    private String NumerPracownika;

    @Column(length = 11)
    private String Pesel;

    @Column(length = 100)
    private String Telefon;

    private Long TypPracownikaId;

    private Long AdresID;

    @Column(length = 50)
    private String Imie;

    @Column(length = 50)
    private String Nazwisko;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
