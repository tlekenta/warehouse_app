package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class W_Sklep {

    @Id
    private Long id;

    private String nazwa;

    private Long adresId;

    @Column(length = 11)
    private String numerSklepu;

    private String telefon;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;


}
