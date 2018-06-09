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
    Long dataId;

    LocalDate data;

    Integer kwartal;

    Integer rok;

    Integer miesiac;

    Integer dzien;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
