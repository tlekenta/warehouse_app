package pl.edu.wat.warehouse_app.zrodlo_pos.model;

import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Entity
public class ZrodloPos_Zwrot implements IBusinessEntity {

    @Id
    private Long id;
    private String receiptCode;
    private String kodKreskowy;
    private String klientNumber;
    private Timestamp date;

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public String getKodKreskowy() {
        return kodKreskowy;
    }

    public void setKodKreskowy(String kodKreskowy) {
        this.kodKreskowy = kodKreskowy;
    }

    public String getKlientNumber() {
        return klientNumber;
    }

    public void setKlientNumber(String klientNumber) {
        this.klientNumber = klientNumber;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public Long getId() { return id; }

    @Override
    @Transient
    public List getBusinessKey() {
        return Arrays.asList(receiptCode, kodKreskowy);
    }
}
