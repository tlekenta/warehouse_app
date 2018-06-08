package pl.edu.wat.warehouse_app.warehouse.model.dimension;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class W_Klient {

    @Id
    Long klientId;

    String imie;

    String nazwisko;

    String email;

    String numerTelefonu;

    @ManyToOne
    W_Adres adresId;

}
