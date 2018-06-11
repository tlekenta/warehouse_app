package pl.edu.wat.warehouse_app.zrodlo_pos.model;

import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
public class ZrodloPos_User implements IBusinessEntity {
    private Long id;
    private String userNumber;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    @Override
    @Transient
    public List getBusinessKey() {
        return Collections.singletonList(userNumber);
    }
}
