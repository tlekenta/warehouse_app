package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class W_Data {

    @Id
    private Long dataId;

    private Date data;

    private Integer kwartal;

    private Integer rok;

    private Integer miesiac;

    private Integer dzien;

}
