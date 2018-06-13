package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class TMP_W_Sklep {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long sklepId;

    @TransformedField(name = "sklep_nazwa")
    private String nazwa;

    private Long adresId;

    @Column(length = 11)
    @TransformedField(name = "sklep_nr")
    private String numerSklepu;

    @TransformedField(name = "sklep_tel")
    private String telefon;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
