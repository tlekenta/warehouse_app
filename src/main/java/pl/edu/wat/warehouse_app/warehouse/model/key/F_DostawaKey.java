package pl.edu.wat.warehouse_app.warehouse.model.key;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class F_DostawaKey implements Serializable {

    private String nrDokumentuDostawy;

    private Integer pozycjaDokumentu;

}
