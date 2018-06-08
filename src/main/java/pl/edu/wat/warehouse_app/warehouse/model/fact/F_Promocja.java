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
    Long promocjaId;

    @ManyToOne
    W_Produkt produktId;

    @ManyToOne
    W_Data dataPoczatkowaId;

    @ManyToOne
    W_Data dataKoncowaId;

    Integer procentObnizki;

}
