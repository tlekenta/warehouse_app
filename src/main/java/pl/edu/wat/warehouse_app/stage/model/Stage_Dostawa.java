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
    private Long id;

    @CsvField(pos = 1, converterType = IntegerConverter.class)
    private Integer lp;

    @Column(length = 100)
    @CsvField(pos = 2)
    private String numerFaktury;

    @Column(length = 100)
    @CsvField(pos = 3)
    private String dostawca;

    @Column(length = 100)
    @CsvField(pos = 4)
    private String produkt;

    @CsvField(pos = 5, converterType = IntegerConverter.class)
    private Integer liczba;

    @CsvField(pos = 6, converterType = FloatConverter.class)
    private Float cenaJednostkowa;

    @CsvField(pos = 7, converterType = IntegerConverter.class)
    private Integer vat;

    @CsvField(pos = 8, converterType = FloatConverter.class)
    private Float kwota;

    @CsvField(pos = 9, converterType = TimeStampConverter.class)
    private Timestamp dataDostawy;

    @CsvField(pos = 10, converterType = TimeStampConverter.class)
    private Timestamp dataZaplaty;

    private Timestamp dataImportu;

}
