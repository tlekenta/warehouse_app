package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class Stage_F_Promocja {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long promocjaId;

    @TransformedField(name = "liczba_porz")
    private Integer lp;

    private Long produktId;

    private Long dataPoczatkowaId;

    private Long dataKoncowaId;

    @TransformedField(name = "procent_obnizki")
    private Integer procentObnizki;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
