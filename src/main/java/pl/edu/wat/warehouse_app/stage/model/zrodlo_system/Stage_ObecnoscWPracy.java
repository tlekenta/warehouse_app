package pl.edu.wat.warehouse_app.stage.model.zrodlo_system;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class Stage_ObecnoscWPracy implements IStageEntity, IBusinessEntity {

    @Id
    private Long Id;

    private Long pracownikId;

    private String numerPracownika;

    private Long sklepId;

    private Timestamp przybycie;

    private Timestamp wyjscie;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

    @Override public List getBusinessKey() {
        return Arrays.asList(numerPracownika, przybycie);
    }
}
