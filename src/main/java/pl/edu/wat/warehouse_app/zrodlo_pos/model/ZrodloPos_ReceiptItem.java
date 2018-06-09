package pl.edu.wat.warehouse_app.zrodlo_pos.model;

import javax.persistence.*;

@Entity
public class ZrodloPos_ReceiptItem {
    private Long id;
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
}
