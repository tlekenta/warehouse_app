package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
public class TMP_W_Czas {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long czasId;

    private Timestamp dateTime;

    private Long dataId;

    private Integer godzina;

    private Integer minuta;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}