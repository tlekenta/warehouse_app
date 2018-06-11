package pl.edu.wat.warehouse_app.zrodlo_system.model;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class ZrodloSystem_Adres implements IBusinessEntity {

    @Id
    private Long id;

    @Column(length = 100)
    private String ulica;

    private Integer numerBudynku;

    private Integer numerLokalu;

    @Override public List getBusinessKey() {
        return Arrays.asList(ulica, numerBudynku, numerLokalu);
    }
}
