package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Stage_W_Data {

    @Id
    private Long dataId;

    private LocalDate data;

    private Integer kwartal;

    private Integer rok;

    private Integer miesiac;

    private Integer dzien;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
