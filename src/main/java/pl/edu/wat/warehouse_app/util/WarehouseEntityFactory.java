package pl.edu.wat.warehouse_app.util;

import org.springframework.stereotype.Component;
import pl.edu.wat.warehouse_app.stage.model.warehouse.*;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.*;
import pl.edu.wat.warehouse_app.warehouse.model.fact.*;

@Component
public class WarehouseEntityFactory {

    public Object getWarehouseEntity(Class tmpTableName) {

        if (tmpTableName == TMP_W_Adres.class) {
            return new W_Adres();
        } else if (tmpTableName == TMP_W_Czas.class) {
            return new W_Czas();
        } else if (tmpTableName == TMP_W_Data.class) {
            return new W_Data();
        } else if (tmpTableName == TMP_W_Klient.class) {
            return new W_Klient();
        } else if (tmpTableName == TMP_W_Pracownik.class) {
            return new W_Pracownik();
        } else if (tmpTableName == TMP_W_Produkt.class) {
            return new W_Produkt();
        } else if (tmpTableName == TMP_W_Sklep.class) {
            return new W_Sklep();
        } else if (tmpTableName == TMP_F_Dostawa.class) {
            return new F_Dostawa();
        } else if (tmpTableName == TMP_F_Promocja.class) {
            return new F_Promocja();
        } else if (tmpTableName == TMP_F_Sprzedaz.class) {
            return new F_Sprzedaz();
        } else if (tmpTableName == TMP_F_Zmiana_Pracownicza.class) {
            return new F_Zmiana_Pracownicza();
        } else if (tmpTableName == TMP_F_Zwrot.class) {
            return new F_Zwrot();
        } else {
            throw new UnsupportedOperationException("Nieobsługiwana encja: " + tmpTableName.getName());
        }
        
    }

}
