package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class W_Czas {

    @Id
    Long czasId;

    LocalDateTime dateTime;

    @ManyToOne
    W_Data dataId;

    Integer godzina;

    Integer minuta;

    Integer sekunda;

}
