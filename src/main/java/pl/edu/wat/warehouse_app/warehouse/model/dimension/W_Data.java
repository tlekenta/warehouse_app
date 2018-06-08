package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class W_Data {

    @Id
    Long dataId;

    LocalDate data;

    Integer kwartal;

    Integer rok;

    Integer miesiac;

    Integer dzien;

}
