package pl.edu.wat.warehouse_app.warehouse.model.fact;

import lombok.Data;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Data;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Pracownik;
import pl.edu.wat.warehouse_app.warehouse.model.dimension.W_Sklep;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class F_Zmiana_Pracownicza {

    @Id
    private Long zmianaId;

    @ManyToOne
    private W_Pracownik pracownikId;

    @ManyToOne
    private W_Data dataRozpoczeciaId;

    @ManyToOne
    private W_Data dataZakonczeniaId;

    @ManyToOne
    private W_Sklep sklepId;

}
