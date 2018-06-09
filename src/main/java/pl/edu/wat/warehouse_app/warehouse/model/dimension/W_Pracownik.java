package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class W_Pracownik {

    @Id
    private Long pracownikId;

    private Integer numerPracownika;

    private String imie;

    private String nazwisko;

    @ManyToOne
    private W_Adres adresId;

    @Column(length = 11)
    private String pesel;

    private String telefon;

}
