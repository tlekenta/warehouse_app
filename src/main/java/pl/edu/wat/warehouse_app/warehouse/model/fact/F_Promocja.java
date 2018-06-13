package pl.edu.wat.warehouse_app.warehouse.model.fact;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class F_Promocja {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private Integer lp;

    private Long produktId;

    private Long dataPoczatkowaId;

    private Long dataKoncowaId;

    private Integer procentObnizki;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;
}
