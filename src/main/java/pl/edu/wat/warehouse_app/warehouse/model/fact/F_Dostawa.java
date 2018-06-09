package pl.edu.wat.warehouse_app.warehouse.model.fact;

import lombok.Data;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Data;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Produkt;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Sklep;
import pl.edu.wat.warehouse_app.warehouse.model.key.F_DostawaKey;

import javax.persistence.*;

@Data
@Entity
public class F_Dostawa {

    @Id
    private F_DostawaKey f_dostawaKey;

    @ManyToOne
    private W_Produkt produktId;

    private Long dostawcaId;

    @ManyToOne
    private W_Data dataId;

    @ManyToOne
    private W_Sklep sklepId;

    private Integer liczbaSztuk;

    private Float cenaJednostkowa;

    private Integer stawkaVat;

    private Float cenaBrutto;

}
