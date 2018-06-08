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
    F_DostawaKey f_dostawaKey;

    @ManyToOne
    W_Produkt produktId;

    Long dostawcaId;

    @ManyToOne
    W_Data dataId;

    @ManyToOne
    W_Sklep sklepId;

    Integer liczbaSztuk;

    Float cenaJednostkowa;

    Integer stawkaVat;

    Float cenaBrutto;

}
