package pl.edu.wat.warehouse_app.util;

import org.springframework.stereotype.Component;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;

import java.lang.reflect.Field;

@Component
public class ReflectionUtils {

    public void rewriteFields(Object pSource, Object pTarget) throws NoSuchFieldException, IllegalAccessException {

        Field[] vSourceFields = pSource.getClass().getDeclaredFields();

        for(Field iSourceField: vSourceFields) {
            String vSourceFieldName = iSourceField.getName();
            Field vTargetField = pTarget.getClass().getDeclaredField(vSourceFieldName);

            rewriteField(iSourceField, vTargetField, pSource, pTarget);
        }
    }

    public void transformFields(Object pSource, Object pTarget) throws IllegalAccessException {
        Field[] vSourceFields = pSource.getClass().getDeclaredFields();
        Field[] vTargetFields = pTarget.getClass().getDeclaredFields();
        for(Field iSourceField: vSourceFields) {
            TransformedField vSourceAnnotation = iSourceField.getAnnotation(TransformedField.class);

            if(vSourceAnnotation != null) {
                for(Field iTargetField: vTargetFields) {
                    TransformedField vTargetAnnotaion = iTargetField.getAnnotation(TransformedField.class);
                    if(vTargetAnnotaion.name().equals(vSourceAnnotation.name())) {
                        if(iSourceField.getType() == Double.TYPE && iTargetField.getType() == Float.class) {
                            rewriteDoubleToFloatField(iSourceField, iTargetField, pSource, pTarget);
                            break;
                        }
                        rewriteField(iSourceField, iTargetField, pSource, pTarget);
                        break;
                    }
                }
            }
        }
    }

    private void rewriteField(Field pSourceField, Field pTargetField, Object pSource, Object pTarget) throws IllegalAccessException {
        pSourceField.setAccessible(true);
        pTargetField.setAccessible(true);

        pTargetField.set(pTarget, pSourceField.get(pSource));

        pSourceField.setAccessible(false);
        pTargetField.setAccessible(false);
    }

    private void rewriteDoubleToFloatField(Field pSourceField, Field pTargetField, Object pSource, Object pTarget) throws IllegalAccessException {
        pSourceField.setAccessible(true);
        pTargetField.setAccessible(true);

        double sourceValue = (double) pSourceField.get(pSource);

        pTargetField.set(pTarget, ((Double) sourceValue).floatValue());

        pSourceField.setAccessible(false);
        pTargetField.setAccessible(false);
    }
}
