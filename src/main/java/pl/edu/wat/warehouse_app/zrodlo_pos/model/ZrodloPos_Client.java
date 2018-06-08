package pl.edu.wat.warehouse_app.zrodlo_pos.model;

import javax.persistence.*;

@Entity
public class ZrodloPos_Client {
    private Long id;
    private String cardnumber;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }
}
