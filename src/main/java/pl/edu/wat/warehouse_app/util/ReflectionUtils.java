package pl.edu.wat.warehouse_app.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class ReflectionUtils {

    public void rewriteFields(Object pSource, Object pTarget) throws NoSuchFieldException, IllegalAccessException {

        Field[] vSourceFields = pSource.getClass().getDeclaredFields();

        for(Field iSourceField: vSourceFields) {
            String vSourceFieldName = iSourceField.getName();
            Field vTargetField = pTarget.getClass().getSuperclass().getDeclaredField(vSourceFieldName);

            iSourceField.setAccessible(true);
            vTargetField.setAccessible(true);

            vTargetField.set(pTarget, iSourceField.get(pSource));

            iSourceField.setAccessible(false);
            vTargetField.setAccessible(false);
        }
    }

}
