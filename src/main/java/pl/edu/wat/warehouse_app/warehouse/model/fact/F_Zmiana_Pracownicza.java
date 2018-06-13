package pl.edu.wat.warehouse_app.warehouse.model.fact;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class F_Zmiana_Pracownicza {


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long zmianaId;

    private Long pracownikId;

    private Long dataRozpoczeciaId;

    private Long dataZakonczeniaId;

    private Long sklepId;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
