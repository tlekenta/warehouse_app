package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Stage_Adres implements IStageEntity {

    @Id
    private Long id;

    @Column(length = 100)
    private String ulica;

    private Integer numerBudynku;

    private Integer numerLokalu;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;
}
