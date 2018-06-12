package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;
import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;
import pl.edu.wat.warehouse_app.util.converter.FloatConverter;
import pl.edu.wat.warehouse_app.util.converter.IntegerConverter;
import pl.edu.wat.warehouse_app.util.converter.TimeStampConverter;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@CsvDataType()
public class Stage_Dostawa {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @CsvField(pos = 1, converterType = IntegerConverter.class)
    private Integer lp;

    @CsvField(pos = 2, converterType = IntegerConverter.class)
    private Integer pozycjaFaktury;

    @Column(length = 100)
    @CsvField(pos = 3)
    private String numerFaktury;

    @Column(length = 100)
    @CsvField(pos = 4)
    private String dostawca;

    @Column(length = 100)
    @CsvField(pos = 5)
    private String kodKreskowy;

    @CsvField(pos = 6, converterType = IntegerConverter.class)
    private Integer liczba;

    @CsvField(pos = 7, converterType = FloatConverter.class)
    private Float cenaJednostkowa;

    @CsvField(pos = 8, converterType = FloatConverter.class)
    private Float kwota;

    @CsvField(pos = 9, converterType = TimeStampConverter.class)
    private Timestamp dataDostawy;

    @CsvField(pos = 10, converterType = TimeStampConverter.class)
    private Timestamp dataZaplaty;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
