package pl.edu.wat.warehouse_app.warehouse.model.key;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class F_ZwrotKey implements Serializable {

    private String nrZwrotu;

    private String numerParagonu;

    private Integer pozycjaParagonu;

}
