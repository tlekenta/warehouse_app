package pl.edu.wat.warehouse_app.warehouse.model.key;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class F_SprzedazKey implements Serializable {

    private String numerParagonu;

    private Integer pozycjaPragonu;

}
