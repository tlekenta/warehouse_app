package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Stage_W_Produkt {

    @Id
    private Long produktId;

    private String nazwa;

    private Float cenaJednostkowa;

    private Integer stawkaVat;

    private Float cenaBrutto;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
