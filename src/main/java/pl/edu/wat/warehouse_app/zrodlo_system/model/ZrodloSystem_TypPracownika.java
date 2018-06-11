package pl.edu.wat.warehouse_app.zrodlo_system.model;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collections;
import java.util.List;

@Entity
@Data
public class ZrodloSystem_TypPracownika implements IBusinessEntity {

    @Id
    private Long Id;

    @Column(length = 100)
    private String typ;

    @Override public List getBusinessKey() {
        return Collections.singletonList(typ);
    }
}
