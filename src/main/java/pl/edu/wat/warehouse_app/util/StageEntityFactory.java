package pl.edu.wat.warehouse_app.util;

import org.springframework.stereotype.Component;
import pl.edu.wat.warehouse_app.stage.model.*;
import pl.edu.wat.warehouse_app.zrodlo_pos.model.*;

@Component
public class StageEntityFactory {

    public IStageEntity getStageEntity(Object pSourceObject) {
        if(pSourceObject.getClass() == ZrodloPos_Client.class) {
            return new Stage_Client();
        } else if(pSourceObject.getClass() == ZrodloPos_Product.class) {
            return new Stage_Product();
        } else if(pSourceObject.getClass() == ZrodloPos_ReciptItem.class) {
            return new Stage_ReciptItem();
        } else if(pSourceObject.getClass() == ZrodloPos_Recipt.class) {
            return new Stage_Recipt();
        } else if(pSourceObject.getClass() == ZrodloPos_Unit.class) {
            return new Stage_Unit();
        } else if(pSourceObject.getClass() == ZrodloPos_User.class) {
            return new Stage_User();
        } else {
            throw new UnsupportedOperationException("Nieobsługiwana encja: " + pSourceObject.getClass().getName());
        }
    }

}
