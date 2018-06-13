package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class W_Adres implements IBusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String ulica;

    private Integer numerDomu;

    private Integer numerMieszkania;

    @Column(length = 6)
    private String kodPocztowy;

    private String miasto;

    private String poczta;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

    @Override
    public List getBusinessKey() {
        return null;
    }
}
