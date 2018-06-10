package pl.edu.wat.warehouse_app.stage.model.zrodlo_system;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class Stage_Adres implements IStageEntity {

    @Id
    private Long id;

    @Column(length = 100)
    private String ulica;

    private Integer numerBudynku;

    private Integer numerLokalu;

    @Column(length = 6)
    private String kodPocztowy;

    private String miasto;

    private String poczta;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;
}
