package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class W_Produkt {

    @Id
    Long produktId;

    String nazwa;

    Float cenaJednostkowa;

    Integer stawkaVat;

    Float cenaBrutto;

}
