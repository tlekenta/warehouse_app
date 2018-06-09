package pl.edu.wat.warehouse_app.stage.model.warehouse;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Stage_F_SprzedazKey implements Serializable {

    String numerParagonu;

    Integer pozycjaPragonu;

}
