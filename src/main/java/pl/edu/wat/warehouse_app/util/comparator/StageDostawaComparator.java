package pl.edu.wat.warehouse_app.util.comparator;

import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.Stage_Dostawa;

@Service
public class StageDostawaComparator {
    public Boolean modelsEqual(Stage_Dostawa stageDostawa, Stage_Dostawa stageDostawa2) {
        Boolean result = true;
        if (!stageDostawa.getDataDostawy().equals(stageDostawa2.getDataDostawy())) {
            result = false;
        }
        if (!stageDostawa.getDataZaplaty().equals(stageDostawa2.getDataZaplaty())) {
            result = false;
        }
        if (!stageDostawa.getDostawca().equals(stageDostawa2.getDostawca())) {
            result = false;
        }
        if (!stageDostawa.getCenaJednostkowa().equals(stageDostawa2.getCenaJednostkowa())) {
            result = false;
        }
        if (!stageDostawa.getKodKreskowy().equals(stageDostawa2.getKodKreskowy())) {
            result = false;
        }
        if (!stageDostawa.getLiczba().equals(stageDostawa2.getLiczba())) {
            result = false;
        }
        if (!stageDostawa.getKwota().equals(stageDostawa2.getKwota())) {
            result = false;
        }

        return result;
    }

}

