package pl.edu.wat.warehouse_app.util.transformer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.warehouse_app.util.DbLogger;
import pl.edu.wat.warehouse_app.util.ReflectionUtils;

@Service
@AllArgsConstructor
public class PromocjaFactTransformer {


    ReflectionUtils reflectionUtils;

    DbLogger logger;

    public void transform() throws IllegalAccessException {
        // TODO transformowanie faktu Promocja
        //Trzeba sprawdzić czy dodawany obiekt już nie istnieje.
    }

}
