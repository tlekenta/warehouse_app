package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;
import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;
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

    @TransformedField(name = "pozycja_dokumentu")
    @CsvField(pos = 2, converterType = IntegerConverter.class)
    private Integer pozycjaFaktury;

    @TransformedField(name = "numer_dokumentu")
    @Column(length = 100)
    @CsvField(pos = 3)
    private String numerFaktury;

    @TransformedField(name = "dostawca")
    @Column(length = 100)
    @CsvField(pos = 4)
    private String dostawca;

    @Column(length = 100)
    @CsvField(pos = 5)
    private String kodKreskowy;

    @TransformedField(name = "sztuki")
    @CsvField(pos = 6, converterType = IntegerConverter.class)
    private Integer liczba;

    @TransformedField(name = "cena_jedn")
    @CsvField(pos = 7, converterType = FloatConverter.class)
    private Float cenaJednostkowa;

    @TransformedField(name = "cena_brutto")
    @CsvField(pos = 8, converterType = FloatConverter.class)
    private Float kwota;

    @CsvField(pos = 9, converterType = TimeStampConverter.class)
    private Timestamp dataDostawy;

    @CsvField(pos = 10, converterType = TimeStampConverter.class)
    private Timestamp dataZaplaty;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
