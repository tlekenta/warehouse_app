package pl.edu.wat.warehouse_app.stage.model.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
public class Stage_W_Produkt {

    @Id
    @GeneratedValue
    private Long produktId;

    private String nazwa;

    private String kodKreskowy;

    private Float cenaJednostkowa;

    private LocalDateTime creationTime;

    private LocalDateTime importTime;

}
