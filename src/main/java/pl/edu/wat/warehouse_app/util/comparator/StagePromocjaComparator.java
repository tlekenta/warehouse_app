package pl.edu.wat.warehouse_app.util.comparator;

import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.stage.model.Stage_Promocja;

@Service
public class StagePromocjaComparator {
    public Boolean modelsEqual(Stage_Promocja stagePromocja, Stage_Promocja stagePromocja2) {
        Boolean result = true;
        if (!stagePromocja.getDataPromocjiDo().equals(stagePromocja2.getDataPromocjiDo())) {
            result = false;
        }
        if (!stagePromocja.getDataPromocjiOd().equals(stagePromocja2.getDataPromocjiOd())) {
            result = false;
        }
        if (!stagePromocja.getProcentPromocji().equals(stagePromocja2.getProcentPromocji())) {
            result = false;
        }
        return result;
    }

}

