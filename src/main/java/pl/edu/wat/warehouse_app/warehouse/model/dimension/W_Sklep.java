package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class W_Sklep implements IBusinessEntity, IStageEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String nazwa;

    private Long adresId;

    @Column(length = 11)
    private String numerSklepu;

    private String telefon;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;


    @Override
    public List getBusinessKey() {
        return null;
    }
}
