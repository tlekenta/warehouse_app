package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;
import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;
import pl.edu.wat.warehouse_app.util.converter.IntegerConverter;
import pl.edu.wat.warehouse_app.util.converter.TimeStampConverter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
@CsvDataType()
public class Stage_Promocja {

    @Id
    @GeneratedValue
    private Long id;

    @CsvField(pos = 1, converterType = IntegerConverter.class)
    private Integer lp;

    @CsvField(pos = 2, converterType = IntegerConverter.class)
    private Integer procentPromocji;

    @CsvField(pos = 3)
    private String kodKreskowy;

    @CsvField(pos = 4, converterType = TimeStampConverter.class)
    private Timestamp dataPromocjiOd;

    @CsvField(pos = 5, converterType = TimeStampConverter.class)
    private Timestamp dataDataPromocjiDo;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
