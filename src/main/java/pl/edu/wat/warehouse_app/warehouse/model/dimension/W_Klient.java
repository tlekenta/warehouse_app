package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class W_Klient implements IBusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(length = 10)
    private String numerKlienta;

    private String imie;

    private String nazwisko;

    private String numerTelefonu;

    private Long adresId;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

    @Override
    public List getBusinessKey() {
        return null;
    }
}
