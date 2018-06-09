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
    private F_SprzedazKey f_sprzedazKey;

    @ManyToOne
    private W_Produkt produktId;

    @ManyToOne
    private W_Pracownik pracownikId;

    @ManyToOne
    private W_Czas czasId;

    @ManyToOne
    private W_Sklep sklepId;

    private Integer liczbaSztuk;

    private Float cenaJednostkowa;

    private Integer stawkaVat;

    private Float cenaBrutto;

}
