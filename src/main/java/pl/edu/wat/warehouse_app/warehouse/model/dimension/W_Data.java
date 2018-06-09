package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class W_Data {

    @Id
    private Long dataId;

    private LocalDate data;

    private Integer kwartal;

    private Integer rok;

    private Integer miesiac;

    private Integer dzien;

}
