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
    private Long czasId;

    private LocalDateTime dateTime;

    @ManyToOne
    private W_Data dataId;

    private Integer godzina;

    private Integer minuta;

    private Integer sekunda;

}
