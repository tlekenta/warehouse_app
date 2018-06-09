package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class Stage_W_Czas {

    @Id
    private Long czasId;

    private LocalDateTime dateTime;

    private Long dataId;

    private Integer godzina;

    private Integer minuta;

    private Integer sekunda;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
