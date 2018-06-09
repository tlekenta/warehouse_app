package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Stage_W_Adres {

    @Id
    private Long adresId;

    private String ulica;

    private Integer numerDomu;

    private Integer numerMieszkania;

    @Column(length = 6)
    private String kodPocztowy;

    private String miasto;

    private String poczta;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
