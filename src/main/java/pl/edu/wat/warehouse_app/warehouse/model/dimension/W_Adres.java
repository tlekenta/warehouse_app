package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class W_Adres {

    @Id
    Long adresId;

    String ulica;

    Integer numerDomu;

    Integer numerMieszkania;

    @Column(length = 6)
    String kodPocztowy;

    String miasto;

    String poczta;

}
