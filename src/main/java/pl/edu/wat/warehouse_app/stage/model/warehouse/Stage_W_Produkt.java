package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Stage_W_Produkt {

    @Id
    @GeneratedValue
    private Long produktId;

    @TransformedField(name = "prod_nazwa")
    private String nazwa;

    @TransformedField(name = "prod_kod")
    private String kodKreskowy;

    @TransformedField(name = "prod_cena")
    private Float cenaJednostkowa;

    private Timestamp creationTime;

    private Timestamp importTime;

}
