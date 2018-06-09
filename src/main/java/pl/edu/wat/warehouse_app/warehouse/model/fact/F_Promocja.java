package pl.edu.wat.warehouse_app.warehouse.model.fact;

import lombok.Data;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Data;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Produkt;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class F_Promocja {

    @Id
    private Long promocjaId;

    @ManyToOne
    private W_Produkt produktId;

    @ManyToOne
    private W_Data dataPoczatkowaId;

    @ManyToOne
    private W_Data dataKoncowaId;

    private Integer procentObnizki;

}
