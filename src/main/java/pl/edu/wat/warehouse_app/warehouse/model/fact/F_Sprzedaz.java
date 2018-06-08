package pl.edu.wat.warehouse_app.warehouse.model.fact;

import lombok.Data;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Czas;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Pracownik;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Produkt;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Sklep;
import pl.edu.wat.warehouse_app.warehouse.model.key.F_SprzedazKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class F_Sprzedaz {

    @Id
    F_SprzedazKey f_sprzedazKey;

    @ManyToOne
    W_Produkt produktId;

    @ManyToOne
    W_Pracownik pracownikId;

    @ManyToOne
    W_Czas czasId;

    @ManyToOne
    W_Sklep sklepId;

    Integer liczbaSztuk;

    Float cenaJednostkowa;

    Integer stawkaVat;

    Float cenaBrutto;

}
