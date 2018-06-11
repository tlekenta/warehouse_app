package pl.edu.wat.warehouse_app.zrodlo_pos.model;

import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Entity
public class ZrodloPos_Receipt implements IBusinessEntity {
    private Long id;
    private String reciptNumber;
    private Long clientId;
    private Long userId;
    private Timestamp date;
    private double totalCost;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public String getReciptNumber() {
        return reciptNumber;
    }

    public void setReciptNumber(String reciptNumber) {
        this.reciptNumber = reciptNumber;
    }

    @Basic
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Basic
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    @Transient
    public List getBusinessKey() {
        return Collections.singletonList(reciptNumber);
    }
}
