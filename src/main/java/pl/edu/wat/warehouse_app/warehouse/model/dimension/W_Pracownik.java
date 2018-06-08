package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class W_Pracownik {

    @Id
    Long pracownikId;

    Integer numerPracownika;

    String imie;

    String nazwisko;

    @ManyToOne
    W_Adres adresId;

    @Column(length = 11)
    String pesel;

    String telefon;

}
