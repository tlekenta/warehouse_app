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
    private F_ZwrotKey f_zwrotKey;

    @ManyToOne
    private W_Pracownik produktId;

    @ManyToOne
    private W_Klient klientId;

    @ManyToOne
    private W_Data dataId;

    @ManyToOne
    private W_Sklep sklepId;

    private Integer liczbaSztuk;

    private Float cenaJednostkowa;

    private Float strataCalkowita;

}
