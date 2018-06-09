package pl.edu.wat.warehouse_app.zrodlo_system.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ZrodloSystem_TypPracownika {

    @Id
    private Long Id;

    @Column(length = 100)
    private String typ;

}
