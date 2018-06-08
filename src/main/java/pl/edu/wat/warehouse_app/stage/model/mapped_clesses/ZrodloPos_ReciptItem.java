package pl.edu.wat.warehouse_app.stage.model.mapped_clesses;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ZrodloPos_ReciptItem {
    private Long id;
    private Long reciptId;
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
    public Long getReciptId() {
        return reciptId;
    }

    public void setReciptId(Long reciptId) {
        this.reciptId = reciptId;
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
