package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class W_Sklep {

    @Id
    private Long sklepId;

    private String nazwa;

    @ManyToOne
    private W_Adres adresId;

    @Column(length = 11)
    private String numerSklepu;

    private String telefon;

}
