package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Adres;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

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

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
