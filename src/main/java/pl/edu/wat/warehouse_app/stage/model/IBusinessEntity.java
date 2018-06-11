package pl.edu.wat.warehouse_app.stage.model;

import java.beans.Transient;
import java.util.List;

public interface IBusinessEntity {

    Long getId();

    @Transient
    List getBusinessKey();

}
