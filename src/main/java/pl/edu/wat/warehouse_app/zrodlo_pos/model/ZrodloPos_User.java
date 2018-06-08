package pl.edu.wat.warehouse_app.zrodlo_pos.model;

import javax.persistence.*;

@Entity
public class ZrodloPos_User {
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
    
}
