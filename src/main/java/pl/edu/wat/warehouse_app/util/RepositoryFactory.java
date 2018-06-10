package pl.edu.wat.warehouse_app.util;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_pos.*;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.*;
import pl.edu.wat.warehouse_app.zrodlo_pos.model.*;
import pl.edu.wat.warehouse_app.zrodlo_pos.repository.*;
import pl.edu.wat.warehouse_app.zrodlo_system.model.*;
import pl.edu.wat.warehouse_app.zrodlo_system.repository.*;

@Component
@AllArgsConstructor
public class RepositoryFactory {

    private Stage_ClientRepository stage_clientRepository;
    private Stage_ProductRepository stage_productRepository;
    private Stage_ReceiptItemRepository stage_reciptItemRepository;
    private Stage_ReceiptRepository stage_reciptRepository;
    private Stage_UnitRepository stage_unitRepository;
    private Stage_UserRepository stage_userRepository;

    private Stage_AdresRepository stage_adresRepository;
    private Stage_KlientRepository stage_klientRepository;
    private Stage_ObecnoscWPracyRepository stage_obecnoscWPracyRepository;
    private Stage_PracownikRepository stage_pracownikRepository;
    private Stage_SklepRepository stage_sklepRepository;
    private Stage_TypPracownikaRepository stage_typPracownikaRepository;

    private ZrodloPos_ClientRepository zrodloPos_clientRepository;
    private ZrodloPos_ProductRepository zrodloPos_productRepository;
    private ZrodloPos_ReceiptItemRepository zrodloPos_reciptItemRepository;
    private ZrodloPos_ReceiptRepository zrodloPos_reciptRepository;
    private ZrodloPos_UnitRepository zrodloPos_unitRepository;
    private ZrodloPos_UserRepository zrodloPos_userRepository;

    private ZrodloPos_AdresRepository zrodloPos_adresRepository;
    private ZrodloPos_KlientRepository zrodloPos_klientRepository;
    private ZrodloPos_ObecnoscWPracyRepository zrodloPos_obecnoscWPracyRepository;
    private ZrodloPos_PracownikRepository zrodloPos_pracownikRepository;
    private ZrodloPos_SklepRepository zrodloPos_sklepRepository;
    private ZrodloPos_TypPracownikaRepository zrodloPos_typPracownikaRepository;

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
        } else if(pClass == ZrodloSystem_Adres.class){
            return stage_adresRepository;
        } else if(pClass == ZrodloSystem_Klient.class) {
            return stage_klientRepository;
        } else if(pClass == ZrodloSystem_ObecnoscWPracy.class) {
            return stage_obecnoscWPracyRepository;
        } else if(pClass == ZrodloSystem_Pracownik.class) {
            return stage_pracownikRepository;
        } else if(pClass == ZrodloSystem_Sklep.class) {
            return stage_sklepRepository;
        } else if(pClass == ZrodloSystem_TypPracownika.class) {
            return stage_typPracownikaRepository;
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
        } else if(pClass == ZrodloSystem_Adres.class){
            return zrodloPos_adresRepository;
        } else if(pClass == ZrodloSystem_Klient.class) {
            return zrodloPos_klientRepository;
        } else if(pClass == ZrodloSystem_ObecnoscWPracy.class) {
            return zrodloPos_obecnoscWPracyRepository;
        } else if(pClass == ZrodloSystem_Pracownik.class) {
            return zrodloPos_pracownikRepository;
        } else if(pClass == ZrodloSystem_Sklep.class) {
            return zrodloPos_sklepRepository;
        } else if(pClass == ZrodloSystem_TypPracownika.class) {
            return zrodloPos_typPracownikaRepository;
        } else {
            throw new UnsupportedOperationException("Nieobsługiwana encja: " + pClass.getName());
        }
    }

}
