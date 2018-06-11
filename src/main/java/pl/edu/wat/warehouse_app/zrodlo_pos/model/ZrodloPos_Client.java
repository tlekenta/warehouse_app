package pl.edu.wat.warehouse_app.zrodlo_pos.model;

import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
public class ZrodloPos_Client implements IBusinessEntity {
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

    @Override
    @Transient
    public List getBusinessKey() {
        return Collections.singletonList(cardnumber);
    }
}
