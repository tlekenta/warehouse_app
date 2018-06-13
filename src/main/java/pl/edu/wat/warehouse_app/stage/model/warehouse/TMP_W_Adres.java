package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TMP_W_Adres implements IBusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @TransformedField(name = "adr_ul")
    private String ulica;

    @TransformedField(name = "adr_nr_domu")
    private Integer numerDomu;

    @TransformedField(name = "adr_nr_mieszk")
    private Integer numerMieszkania;

    @Column(length = 6)
    @TransformedField(name = "adr_kod")
    private String kodPocztowy;

    @TransformedField(name = "adr_miasto")
    private String miasto;

    @TransformedField(name = "adr_poczta")
    private String poczta;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

    @Override
    public List getBusinessKey() {
        return null;
    }
}
