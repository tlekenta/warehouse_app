package pl.edu.wat.warehouse_app.stage.model.zrodlo_system;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class Stage_Sklep implements IStageEntity {

    @Id
    private Long Id;

    @Column(length = 10)
    private String NumerSklepu;

    @Column(length = 50)
    private String Nazwa;

    @Column(length = 101)
    private String Telefon;

    private Long AdresID;

    private Timestamp creationTime;

    private Timestamp importTime;
}
