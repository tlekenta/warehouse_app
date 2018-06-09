package pl.edu.wat.warehouse_app.stage.model.zrodlo_system;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class Stage_Klient implements IStageEntity {

    @Id
    private Long Id;

    @Column(length = 10)
    private String NumerKlienta;

    @Column(length = 100)
    private String Telefon;

    private Long AdresId;

    @Column(length = 50)
    private String Imie;

    @Column(length = 50)
    private String Nazwisko;

    private Timestamp creationTime;

    private Timestamp importTime;

}
