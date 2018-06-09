package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Stage_W_Produkt {

    @Id
    Long produktId;

    String nazwa;

    Float cenaJednostkowa;

    Integer stawkaVat;

    Float cenaBrutto;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
