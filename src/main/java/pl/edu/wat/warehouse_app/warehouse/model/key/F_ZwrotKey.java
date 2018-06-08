package pl.edu.wat.warehouse_app.warehouse.model.key;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class F_ZwrotKey implements Serializable {

    String nrZwrotu;

    String numerParagonu;

    Integer pozycjaParagonu;

}
