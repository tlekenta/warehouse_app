package pl.edu.wat.warehouse_app.stage.model.zrodlo_system;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Stage_ObecnoscWPracy {

    @Id
    private Long Id;

    private Long pracownikId;

    private Long sklepId;

    private LocalDateTime przybycie;

    private LocalDateTime wyjscie;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
