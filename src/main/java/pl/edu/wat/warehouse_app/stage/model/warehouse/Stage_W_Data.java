package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Stage_W_Data {

    @Id
    @GeneratedValue
    private Long dataId;

    private Date data;

    private Integer kwartal;

    private Integer rok;

    private Integer miesiac;

    private Integer dzien;

    private Timestamp creationTime;

    private Timestamp importTime;

}
