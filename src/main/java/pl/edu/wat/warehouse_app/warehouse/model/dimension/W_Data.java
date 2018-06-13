package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class W_Data implements IBusinessEntity, IStageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private Date data;

    private Integer kwartal;

    private Integer rok;

    private Integer miesiac;

    private Integer dzien;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

    @Override
    public List getBusinessKey() {
        return null;
    }
}
