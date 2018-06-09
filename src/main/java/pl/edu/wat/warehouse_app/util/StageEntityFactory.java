package pl.edu.wat.warehouse_app.util;

import org.springframework.stereotype.Component;
import pl.edu.wat.warehouse_app.stage.model.*;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_pos.*;
import pl.edu.wat.warehouse_app.zrodlo_pos.model.*;

@Component
public class StageEntityFactory {

    public IStageEntity getStageEntity(Object pSourceObject) {
        if(pSourceObject.getClass() == ZrodloPos_Client.class) {
            return new Stage_Client();
        } else if(pSourceObject.getClass() == ZrodloPos_Product.class) {
            return new Stage_Product();
        } else if(pSourceObject.getClass() == ZrodloPos_ReceiptItem.class) {
            return new Stage_ReceiptItem();
        } else if(pSourceObject.getClass() == ZrodloPos_Receipt.class) {
            return new Stage_Receipt();
        } else if(pSourceObject.getClass() == ZrodloPos_Unit.class) {
            return new Stage_Unit();
        } else if(pSourceObject.getClass() == ZrodloPos_User.class) {
            return new Stage_User();
        } else {
            throw new UnsupportedOperationException("Nieobsługiwana encja: " + pSourceObject.getClass().getName());
        }
    }

}
