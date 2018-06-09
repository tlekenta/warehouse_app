package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@Entity
public class W_Czas {

    @Id
    private Long czasId;

    private Timestamp dateTime;

    @ManyToOne
    private W_Data dataId;

    private Integer godzina;

    private Integer minuta;

    private Integer sekunda;

}
