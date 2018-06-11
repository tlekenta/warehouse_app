package pl.edu.wat.warehouse_app.stage.model.zrodlo_system;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class Stage_Adres implements IStageEntity, IBusinessEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100)
    @TransformedField(name = "adr_ul")
    private String ulica;

    @TransformedField(name = "adr_nr_domu")
    private Integer numerBudynku;

    @TransformedField(name = "adr_nr_mieszk")
    private Integer numerLokalu;

    @Column(length = 6)
    @TransformedField(name = "adr_kod")
    private String kodPocztowy;

    @TransformedField(name = "adr_miasto")
    private String miasto;

    @TransformedField(name = "adr_poczta")
    private String poczta;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

    @Override public List getBusinessKey() {
        return Arrays.asList(ulica, numerBudynku, numerLokalu);
    }
}
