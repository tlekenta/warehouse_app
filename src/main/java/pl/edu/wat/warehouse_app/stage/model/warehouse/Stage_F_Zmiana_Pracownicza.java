package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class Stage_F_Zmiana_Pracownicza {

    @Id
    private Long zmianaId;

    private Long pracownikId;

    private Long dataRozpoczeciaId;

    private Long dataZakonczeniaId;

    private Long sklepId;

    private Timestamp creationTime;

    private Timestamp importTime;

}
