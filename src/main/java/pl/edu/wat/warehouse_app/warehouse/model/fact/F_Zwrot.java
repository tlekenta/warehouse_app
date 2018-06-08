package pl.edu.wat.warehouse_app.warehouse.model.fact;

import lombok.Data;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.*;
import pl.edu.wat.warehouse_app.warehouse.model.key.F_ZwrotKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class F_Zwrot {

    @Id
    F_ZwrotKey f_zwrotKey;

    @ManyToOne
    W_Pracownik produktId;

    @ManyToOne
    W_Klient klientId;

    @ManyToOne
    W_Data dataId;

    @ManyToOne
    W_Sklep sklepId;

    Integer liczbaSztuk;

    Float cenaJednostkowa;

    Float strataCalkowita;

}
