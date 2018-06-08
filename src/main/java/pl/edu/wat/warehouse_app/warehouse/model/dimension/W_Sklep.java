package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class W_Sklep {

    @Id
    Long sklepId;

    String nazwa;

    @ManyToOne
    W_Adres adresId;

    @Column(length = 11)
    String numerSklepu;

    String telefon;

}
