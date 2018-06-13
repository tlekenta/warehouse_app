package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;
import pl.edu.wat.warehouse_app.stage.model.IBusinessEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class W_Pracownik implements IBusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String numerPracownika;

    private String imie;

    private String nazwisko;

    private Long adresId;

    @Column(length = 11)
    private String pesel;

    private String telefon;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

    @Override
    public List getBusinessKey() {
        return null;
    }
}
