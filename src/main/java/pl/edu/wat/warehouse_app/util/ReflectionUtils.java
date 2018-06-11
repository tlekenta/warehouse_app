package pl.edu.wat.warehouse_app.util;

import org.springframework.stereotype.Component;
import pl.edu.wat.warehouse_app.util.annotation.TransformedField;

import javax.persistence.Id;
import java.lang.reflect.Field;

@Component
public class ReflectionUtils {

    /**
     * Porównuje proste typy w polach o takich samych nazwach. Wykorzystywanie ma sens tylko gdy obiekty są innych klas.
     * Jeżeli pierwszy parametr ma więcej pól niż drugi - wywali błąd
     * @return false jeżeli obiekty się róznią, true w pp
     */
    public boolean compareFields(Object pSource, Object pTarget) throws NoSuchFieldException, IllegalAccessException {
        Field[] vSourceFields = pSource.getClass().getDeclaredFields();

        for(Field iSourceField: vSourceFields) {
            if(iSourceField.getAnnotation(Id.class) != null)
                continue;
            String vSourceFieldName = iSourceField.getName();
            Field vTargetField = pTarget.getClass().getDeclaredField(vSourceFieldName);

            if(iSourceField.getType() == Double.TYPE && vTargetField.getType() == Float.class) {
                if(!equalDoubleAndFloatFields(iSourceField, vTargetField, pSource, pTarget))
                    return false;
            } else {
                if(!equalFields(iSourceField, vTargetField, pSource, pTarget))
                    return false;
            }
        }
        return true;
    }

    public void rewriteFields(Object pSource, Object pTarget) throws NoSuchFieldException, IllegalAccessException {

        Field[] vSourceFields = pSource.getClass().getDeclaredFields();

        for(Field iSourceField: vSourceFields) {
            if(iSourceField.getAnnotation(Id.class) != null)
                continue;
            String vSourceFieldName = iSourceField.getName();
            Field vTargetField = pTarget.getClass().getDeclaredField(vSourceFieldName);

            if(iSourceField.getType() == Double.TYPE && vTargetField.getType() == Float.class) {
                rewriteDoubleToFloatField(iSourceField, vTargetField, pSource, pTarget);
            } else {
                rewriteField(iSourceField, vTargetField, pSource, pTarget);
            }
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

                    if(vTargetAnnotaion == null)
                        continue;

                    if(vTargetAnnotaion.name().equals(vSourceAnnotation.name())) {

                        //double do Float
                        if(iSourceField.getType() == Double.TYPE && iTargetField.getType() == Float.class) {
                            rewriteDoubleToFloatField(iSourceField, iTargetField, pSource, pTarget);
                            break;
                        } else { //jednakowe typy
                            rewriteField(iSourceField, iTargetField, pSource, pTarget);
                            break;
                        }
                    }
                }
            }
        }
    }

    @Deprecated
    public boolean compareAndRewriteFields(Object pSource, Object pTarget) throws IllegalAccessException {
        boolean change = false;
        Field[] vSourceFields = pSource.getClass().getDeclaredFields();
        Field[] vTargetFields = pTarget.getClass().getDeclaredFields();
        for(Field iSourceField: vSourceFields) {
            TransformedField vSourceAnnotation = iSourceField.getAnnotation(TransformedField.class);

            if(vSourceAnnotation != null) {

                for(Field iTargetField: vTargetFields) {
                    TransformedField vTargetAnnotaion = iTargetField.getAnnotation(TransformedField.class);

                    if(vTargetAnnotaion == null)
                        continue;

                    if(vTargetAnnotaion.name().equals(vSourceAnnotation.name())) {

                        //double do Float
                        if(iSourceField.getType() == Double.TYPE && iTargetField.getType() == Float.class) {
                            if(!equalDoubleAndFloatFields(iSourceField, iTargetField, pSource, pTarget)){
                                change = true;
                                rewriteDoubleToFloatField(iSourceField, iTargetField, pSource, pTarget);
                                break;
                            }
                        } else if(!equalFields(iSourceField, iTargetField, pSource, pTarget)) { //jednakowe typy
                            change = true;
                            rewriteField(iSourceField, iTargetField, pSource, pTarget);
                            break;
                        }
                    }
                }
            }
        }

        return change;
    }

    private void rewriteField(Field pSourceField, Field pTargetField, Object pSource, Object pTarget) throws IllegalAccessException {
        pSourceField.setAccessible(true);
        pTargetField.setAccessible(true);

        pTargetField.set(pTarget, pSourceField.get(pSource));

        pSourceField.setAccessible(false);
        pTargetField.setAccessible(false);
    }

    private boolean equalFields(Field pSourceField, Field pTargetField, Object pSource, Object pTarget) throws IllegalAccessException {
        pSourceField.setAccessible(true);
        pTargetField.setAccessible(true);

        return pTargetField.get(pTarget).equals(pSourceField.get(pSource));
    }

    private boolean equalDoubleAndFloatFields(Field pSourceField, Field pTargetField, Object pSource, Object pTarget) throws IllegalAccessException {
        pSourceField.setAccessible(true);
        pTargetField.setAccessible(true);

        double sourceValue = (double) pSourceField.get(pSource);
        Float toCompare = ((Double) sourceValue).floatValue();

        return pTargetField.get(pTarget).equals(toCompare);
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
