package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class W_Klient {

    @Id
    private Long klientId;

    private String imie;

    private String nazwisko;

    private String email;

    private String numerTelefonu;

    @ManyToOne
    private W_Adres adresId;

}
