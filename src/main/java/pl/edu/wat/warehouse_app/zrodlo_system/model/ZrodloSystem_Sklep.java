package pl.edu.wat.warehouse_app.zrodlo_system.model;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Data
public class ZrodloSystem_Sklep implements IBusinessEntity {

    @Id
    private Long Id;

    @Column(length = 10)
    private String NumerSklepu;

    @Column(length = 50)
    private String Nazwa;

    @Column(length = 101)
    private String Telefon;

    private Long AdresID;

    @Override public List getBusinessKey() {
        return Collections.singletonList(NumerSklepu);
    }
}
