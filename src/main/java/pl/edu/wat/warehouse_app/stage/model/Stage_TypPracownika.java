package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Stage_TypPracownika implements IStageEntity {

    @Id
    private Long Id;

    @Column(length = 100)
    private String typ;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
