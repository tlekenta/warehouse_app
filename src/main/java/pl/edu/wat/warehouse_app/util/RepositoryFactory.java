package pl.edu.wat.warehouse_app.util;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pl.edu.wat.warehouse_app.stage.repository.*;
import pl.edu.wat.warehouse_app.zrodlo_pos.model.*;
import pl.edu.wat.warehouse_app.zrodlo_pos.repository.*;

@Component
@AllArgsConstructor
public class RepositoryFactory {

    private Stage_ClientRepository stage_clientRepository;
    private Stage_ProductRepository stage_productRepository;
    private Stage_ReceiptItemRepository stage_reciptItemRepository;
    private Stage_ReceiptRepository stage_reciptRepository;
    private Stage_UnitRepository stage_unitRepository;
    private Stage_UserRepository stage_userRepository;

    private ZrodloPos_ClientRepository zrodloPos_clientRepository;
    private ZrodloPos_ProductRepository zrodloPos_productRepository;
    private ZrodloPos_ReceiptItemRepository zrodloPos_reciptItemRepository;
    private ZrodloPos_ReceiptRepository zrodloPos_reciptRepository;
    private ZrodloPos_UnitRepository zrodloPos_unitRepository;
    private ZrodloPos_UserRepository zrodloPos_userRepository;

    public JpaRepository getStageRepository(Class pClass) {
        if(pClass == ZrodloPos_Client.class) {
            return stage_clientRepository;
        } else if(pClass == ZrodloPos_Product.class) {
            return stage_productRepository;
        } else if(pClass == ZrodloPos_ReceiptItem.class) {
            return stage_reciptItemRepository;
        } else if(pClass == ZrodloPos_Receipt.class) {
            return stage_reciptRepository;
        } else if(pClass == ZrodloPos_Unit.class) {
            return stage_unitRepository;
        } else if(pClass == ZrodloPos_User.class) {
            return stage_userRepository;
        } else {
            throw new UnsupportedOperationException("Nieobsługiwana encja: " + pClass.getName());
        }
    }

    public JpaRepository getSourceRepository(Class pClass) {
        if(pClass == ZrodloPos_Client.class) {
            return zrodloPos_clientRepository;
        } else if(pClass == ZrodloPos_Product.class) {
            return zrodloPos_productRepository;
        } else if(pClass == ZrodloPos_ReceiptItem.class) {
            return zrodloPos_reciptItemRepository;
        } else if(pClass == ZrodloPos_Receipt.class) {
            return zrodloPos_reciptRepository;
        } else if(pClass == ZrodloPos_Unit.class) {
            return zrodloPos_unitRepository;
        } else if(pClass == ZrodloPos_User.class) {
            return zrodloPos_userRepository;
        } else {
            throw new UnsupportedOperationException("Nieobsługiwana encja: " + pClass.getName());
        }
    }

}
