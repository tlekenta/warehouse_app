package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class W_Produkt {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long produktId;

    private String nazwa;

    private String kodKreskowy;

    private Float cenaJednostkowa;

    private Timestamp timestampFrom;

    private Timestamp timestampTo;

}
