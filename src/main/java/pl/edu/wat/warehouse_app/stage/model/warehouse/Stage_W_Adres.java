package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
public class Stage_W_Adres {

    @Id
    @GeneratedValue
    private Long adresId;

    private String ulica;

    private Integer numerDomu;

    private Integer numerMieszkania;

    @Column(length = 6)
    private String kodPocztowy;

    private String miasto;

    private String poczta;

    private Timestamp creationTime;

    private Timestamp importTime;

}
