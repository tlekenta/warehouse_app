package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;
import pl.edu.wat.warehouse_app.warehouse.model.key.F_SprzedazKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Stage_F_Sprzedaz {

    @Id
    private F_SprzedazKey f_sprzedazKey;

    private Long produktId;

    private Long pracownikId;

    private Long czasId;

    private Long sklepId;

    private Integer liczbaSztuk;

    private Float cenaJednostkowa;

    private Integer stawkaVat;

    private Float cenaBrutto;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
