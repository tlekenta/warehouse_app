package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class Stage_W_Sklep {

    @Id
    private Long sklepId;

    private String nazwa;

    private Long adresId;

    @Column(length = 11)
    private String numerSklepu;

    private String telefon;

    private Timestamp creationTime;

    private Timestamp importTime;

}
