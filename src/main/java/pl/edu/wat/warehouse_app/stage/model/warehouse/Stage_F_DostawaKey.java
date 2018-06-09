package pl.edu.wat.warehouse_app.stage.model.warehouse;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Stage_F_DostawaKey implements Serializable {

    private String nrDokumentuDostawy;

    private Integer pozycjaDokumentu;

}
