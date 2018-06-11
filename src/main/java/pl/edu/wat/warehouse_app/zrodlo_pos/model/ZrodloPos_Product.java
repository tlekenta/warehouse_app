package pl.edu.wat.warehouse_app.zrodlo_pos.model;

import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
public class ZrodloPos_Product implements IBusinessEntity {
    private Long id;
    private Long unitId;
    private String name;
    private double value;
    private String barcode;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Basic
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    @Transient
    public List getBusinessKey() {
        return Collections.singletonList(barcode);
    }
}
