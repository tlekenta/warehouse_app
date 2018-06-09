package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class Stage_W_Czas {

    @Id
    private Long czasId;

    private Timestamp dateTime;

    private Long dataId;

    private Integer godzina;

    private Integer minuta;

    private Integer sekunda;

    private Timestamp creationTime;

    private Timestamp importTime;

}
