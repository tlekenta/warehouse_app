package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class TMP_W_Sklep implements IBusinessEntity, IStageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

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

    @Override
    public List getBusinessKey() {
        return null;
    }
}
