package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Stage_F_Promocja {

    @Id
    private Long promocjaId;

    private Long produktId;

    private Long dataPoczatkowaId;

    private Long dataKoncowaId;

    private Integer procentObnizki;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
