package pl.edu.wat.warehouse_app.stage.model.warehouse;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Stage_F_ZwrotKey implements Serializable {

    private String nrZwrotu;

    private String numerParagonu;

    private Integer pozycjaParagonu;
}
