package pl.edu.wat.warehouse_app.util;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pl.edu.wat.warehouse_app.stage.model.warehouse.*;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_pos.*;
import pl.edu.wat.warehouse_app.stage.model.zrodlo_system.*;
import pl.edu.wat.warehouse_app.stage.repository.warehouse.*;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_pos.*;
import pl.edu.wat.warehouse_app.stage.repository.zrodlo_system.*;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.*;
import pl.edu.wat.warehouse_app.warehouse.model.fact.*;
import pl.edu.wat.warehouse_app.warehouse.repository.dimension.*;
import pl.edu.wat.warehouse_app.warehouse.repository.fact.*;
import pl.edu.wat.warehouse_app.zrodlo_pos.model.*;
import pl.edu.wat.warehouse_app.zrodlo_pos.repository.*;
import pl.edu.wat.warehouse_app.zrodlo_system.model.*;
import pl.edu.wat.warehouse_app.zrodlo_system.repository.*;

@Component
@AllArgsConstructor
public class RepositoryFactory {

    private W_AdresRepository w_adresRepository;
    private W_CzasRepository w_czasRepository;
    private W_DataRepository w_dataRepository;
    private W_KlientRepository w_klientRepository;
    private W_PracownikRepository w_pracownikRepository;
    private W_ProduktRepository w_produktRepository;
    private W_SklepRepository w_sklepRepository;
    private F_DostawaRepository f_dostawaRepository;
    private F_PromocjaRepository f_promocjaRepository;
    private F_SprzedazRepository f_sprzedazRepository;
    private F_Zmiana_PracowniczaRepository f_zmianaPracowniczaRepository;
    private F_ZwrotRepository f_zwrotRepository;

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

    private Stage_W_AdresRepository stage_w_adresRepository;
    private Stage_W_CzasRepository stage_w_czasRepository;
    private Stage_W_DataRepository stage_w_dataRepository;
    private Stage_W_KlientRepository stage_w_klientRepository;
    private Stage_W_PracownikRepository stage_w_pracownikRepository;
    private Stage_W_ProductRepository stage_w_produktRepository;
    private Stage_W_SklepRepository stage_w_sklepRepository;
    private Stage_F_DostawaRepository stage_f_dostawaRepository;
    private Stage_F_PromocjaRepository stage_f_promocjaRepository;
    private Stage_F_SprzedazRepository stage_f_sprzedazRepository;
    private Stage_F_Zmiana_PracowniczaRepository stage_f_zmianaPracowniczaRepository;
    private Stage_F_ZwrotRepository stage_f_zwrotRepository;

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
        if (pClass == ZrodloPos_Client.class) {
            return stage_clientRepository;
        } else if (pClass == ZrodloPos_Product.class) {
            return stage_productRepository;
        } else if (pClass == ZrodloPos_ReceiptItem.class) {
            return stage_reciptItemRepository;
        } else if (pClass == ZrodloPos_Receipt.class) {
            return stage_reciptRepository;
        } else if (pClass == ZrodloPos_Unit.class) {
            return stage_unitRepository;
        } else if (pClass == ZrodloPos_User.class) {
            return stage_userRepository;
        } else if (pClass == ZrodloSystem_Adres.class) {
            return stage_adresRepository;
        } else if (pClass == ZrodloSystem_Klient.class) {
            return stage_klientRepository;
        } else if (pClass == ZrodloSystem_ObecnoscWPracy.class) {
            return stage_obecnoscWPracyRepository;
        } else if (pClass == ZrodloSystem_Pracownik.class) {
            return stage_pracownikRepository;
        } else if (pClass == ZrodloSystem_Sklep.class) {
            return stage_sklepRepository;
        } else if (pClass == ZrodloSystem_TypPracownika.class) {
            return stage_typPracownikaRepository;
        } else {
            throw new UnsupportedOperationException("Nieobsługiwana encja: " + pClass.getName());
        }
    }

    public JpaRepository getStageRepositoryByStageClass(Class pClass) {
        if (pClass == Stage_Client.class) {
            return stage_clientRepository;
        } else if (pClass == Stage_Product.class) {
            return stage_productRepository;
        } else if (pClass == Stage_ReceiptItem.class) {
            return stage_reciptItemRepository;
        } else if (pClass == Stage_Receipt.class) {
            return stage_reciptRepository;
        } else if (pClass == Stage_Unit.class) {
            return stage_unitRepository;
        } else if (pClass == Stage_User.class) {
            return stage_userRepository;
        } else if (pClass == Stage_Adres.class) {
            return stage_adresRepository;
        } else if (pClass == Stage_Klient.class) {
            return stage_klientRepository;
        } else if (pClass == Stage_ObecnoscWPracy.class) {
            return stage_obecnoscWPracyRepository;
        } else if (pClass == Stage_Pracownik.class) {
            return stage_pracownikRepository;
        } else if (pClass == Stage_Sklep.class) {
            return stage_sklepRepository;
        } else if (pClass == Stage_TypPracownika.class) {
            return stage_typPracownikaRepository;
        } else if (pClass == Stage_W_Adres.class) {
            return stage_w_adresRepository;
        } else if (pClass == Stage_W_Czas.class) {
            return stage_w_czasRepository;
        } else if (pClass == Stage_W_Data.class) {
            return stage_w_dataRepository;
        } else if (pClass == Stage_W_Klient.class) {
            return stage_w_klientRepository;
        } else if (pClass == Stage_W_Pracownik.class) {
            return stage_w_pracownikRepository;
        } else if (pClass == Stage_W_Produkt.class) {
            return stage_w_produktRepository;
        } else if (pClass == Stage_W_Sklep.class) {
            return stage_w_sklepRepository;
        } else if (pClass == Stage_F_Dostawa.class) {
            return stage_f_dostawaRepository;
        } else if (pClass == Stage_F_Promocja.class) {
            return stage_f_promocjaRepository;
        } else if (pClass == Stage_F_Sprzedaz.class) {
            return stage_f_sprzedazRepository;
        } else if (pClass == Stage_F_Zmiana_Pracownicza.class) {
            return stage_f_zmianaPracowniczaRepository;
        } else if (pClass == Stage_F_Zwrot.class) {
            return stage_f_zwrotRepository;
        } else {
            throw new UnsupportedOperationException("Nieobsługiwana encja: " + pClass.getName());
        }
    }

    public JpaRepository getSourceRepository(Class pClass) {
        if (pClass == ZrodloPos_Client.class) {
            return zrodloPos_clientRepository;
        } else if (pClass == ZrodloPos_Product.class) {
            return zrodloPos_productRepository;
        } else if (pClass == ZrodloPos_ReceiptItem.class) {
            return zrodloPos_reciptItemRepository;
        } else if (pClass == ZrodloPos_Receipt.class) {
            return zrodloPos_reciptRepository;
        } else if (pClass == ZrodloPos_Unit.class) {
            return zrodloPos_unitRepository;
        } else if (pClass == ZrodloPos_User.class) {
            return zrodloPos_userRepository;
        } else if (pClass == ZrodloSystem_Adres.class) {
            return zrodloPos_adresRepository;
        } else if (pClass == ZrodloSystem_Klient.class) {
            return zrodloPos_klientRepository;
        } else if (pClass == ZrodloSystem_ObecnoscWPracy.class) {
            return zrodloPos_obecnoscWPracyRepository;
        } else if (pClass == ZrodloSystem_Pracownik.class) {
            return zrodloPos_pracownikRepository;
        } else if (pClass == ZrodloSystem_Sklep.class) {
            return zrodloPos_sklepRepository;
        } else if (pClass == ZrodloSystem_TypPracownika.class) {
            return zrodloPos_typPracownikaRepository;
        } else {
            throw new UnsupportedOperationException("Nieobsługiwana encja: " + pClass.getName());
        }
    }

    public JpaRepository getWarehouseRepository(Class pClass) {
        if (W_Adres.class == pClass) {
            return w_adresRepository;
        }
        if (W_Czas.class == pClass) {
            return w_czasRepository;
        }
        if (W_Data.class == pClass) {
            return w_dataRepository;
        }
        if (W_Klient.class == pClass) {
            return w_klientRepository;
        }
        if (W_Pracownik.class == pClass) {
            return w_pracownikRepository;
        }
        if (W_Produkt.class == pClass) {
            return w_produktRepository;
        }
        if (W_Sklep.class == pClass) {
            return w_sklepRepository;
        }
        if (F_Dostawa.class == pClass) {
            return f_dostawaRepository;
        }
        if (F_Promocja.class == pClass) {
            return f_promocjaRepository;
        }
        if (F_Sprzedaz.class == pClass) {
            return f_sprzedazRepository;
        }
        if (F_Zmiana_Pracownicza.class == pClass) {
            return f_zmianaPracowniczaRepository;
        }
        if (F_Zwrot.class == pClass) {
            return f_zwrotRepository;
        }

        throw new UnsupportedOperationException("Nieobsługiwana encja: " + pClass.getName());
    }

}
