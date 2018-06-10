package pl.edu.wat.warehouse_app.util;

import org.springframework.stereotype.Component;
import pl.edu.wat.warehouse_app.stage.model.*;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_pos.*;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.*;
import pl.edu.wat.warehouse_app.zrodlo_pos.model.*;
import pl.edu.wat.warehouse_app.zrodlo_system.model.*;

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
        } else if(pSourceObject.getClass() == ZrodloSystem_Adres.class){
            return new Stage_Adres();
        } else if(pSourceObject.getClass() == ZrodloSystem_Klient.class) {
            return new Stage_Klient();
        } else if(pSourceObject.getClass() == ZrodloSystem_ObecnoscWPracy.class) {
            return new Stage_ObecnoscWPracy();
        } else if(pSourceObject.getClass() == ZrodloSystem_Pracownik.class) {
            return new Stage_Pracownik();
        } else if(pSourceObject.getClass() == ZrodloSystem_Sklep.class) {
            return new Stage_Sklep();
        } else if(pSourceObject.getClass() == ZrodloSystem_TypPracownika.class) {
            return new Stage_TypPracownika();
        } else {
            throw new UnsupportedOperationException("Nieobsługiwana encja: " + pSourceObject.getClass().getName());
        }
    }

}
