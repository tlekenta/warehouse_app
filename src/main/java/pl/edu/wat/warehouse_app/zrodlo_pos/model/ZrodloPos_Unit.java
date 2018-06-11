package pl.edu.wat.warehouse_app.zrodlo_pos.model;

import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
public class ZrodloPos_Unit implements IBusinessEntity {
    private Long id;
    private String unitName;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Override public List getBusinessKey() {
        return Collections.singletonList(unitName);
    }
}
