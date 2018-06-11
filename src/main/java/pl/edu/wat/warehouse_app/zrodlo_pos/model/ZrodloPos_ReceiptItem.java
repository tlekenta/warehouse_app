package pl.edu.wat.warehouse_app.zrodlo_pos.model;

import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class ZrodloPos_ReceiptItem implements IBusinessEntity {
    private Long id;
    private String reciptNumber;
    private Integer position;
    private Long receiptId;
    private Long productId;
    private double amount;

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
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Basic
    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    @Basic
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Basic
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override public List getBusinessKey() {
        return Arrays.asList(reciptNumber, position);
    }
}
