package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class W_Produkt {

    @Id
    private Long produktId;

    private String nazwa;

    private Float cenaJednostkowa;

}
