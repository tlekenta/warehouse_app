package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class W_Adres {

    @Id
    private Long adresId;

    private String ulica;

    private Integer numerDomu;

    private Integer numerMieszkania;

    @Column(length = 6)
    private String kodPocztowy;

    private String miasto;

    private String poczta;

}
