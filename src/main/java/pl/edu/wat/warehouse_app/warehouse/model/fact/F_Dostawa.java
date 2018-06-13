package pl.edu.wat.warehouse_app.warehouse.model.fact;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;
import pl.edu.wat.warehouse_app.stage.model.IStageEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class F_Dostawa implements IBusinessEntity, IStageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    //klucz biznesowy
    private String nrDokumentuDostawy;

    //klucz biznesowy
    private Integer pozycjaDokumentu;

    private Long produktId;

    private Long dataDostawyId;

    private Long dataZaplatyId;

    private Long sklepId;

    private String dostawca;

    private Integer liczbaSztuk;

    private Float cenaJednostkowa;

    private Float cenaBrutto;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

    @Override
    public List getBusinessKey() {
        return null;
    }
}
