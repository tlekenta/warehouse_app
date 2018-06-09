package pl.edu.wat.warehouse_app.stage.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Stage_F_Zmiana_Pracownicza {

    @Id
    private Long zmianaId;

    private Long pracownikId;

    private Long dataRozpoczeciaId;

    private Long dataZakonczeniaId;

    private Long sklepId;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
