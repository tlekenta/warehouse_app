package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;
import pl.edu.wat.warehouse_app.warehouse.model.key.F_ZwrotKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class Stage_F_Zwrot {

    @Id
    private F_ZwrotKey f_zwrotKey;

    private Long produktId;

    private Long klientId;

    private Long dataId;

    private Long sklepId;

    private Integer liczbaSztuk;

    private Float cenaJednostkowa;

    private Float strataCalkowita;

    private Timestamp creationTime;

    private Timestamp importTime;
}
