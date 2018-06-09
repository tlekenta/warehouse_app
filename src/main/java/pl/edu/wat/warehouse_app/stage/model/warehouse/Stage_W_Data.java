package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
public class Stage_W_Data {

    @Id
    private Long dataId;

    private Date data;

    private Integer kwartal;

    private Integer rok;

    private Integer miesiac;

    private Integer dzien;

    private Timestamp creationTime;

    private Timestamp importTime;

}
