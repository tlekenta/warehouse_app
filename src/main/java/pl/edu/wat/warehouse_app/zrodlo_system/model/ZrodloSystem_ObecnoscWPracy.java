package pl.edu.wat.warehouse_app.zrodlo_system.model;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class ZrodloSystem_ObecnoscWPracy implements IBusinessEntity {

    @Id
    private Long Id;

    private Long pracownikId;

    private String numerPracownika;

    private Long sklepId;

    private Timestamp przybycie;

    private Timestamp wyjscie;

    @Override public List getBusinessKey() {
        return Arrays.asList(numerPracownika, przybycie);
    }
}
