package pl.edu.wat.warehouse_app.util.converter;

import net.sf.jsefa.common.converter.SimpleTypeConverter;

public class FloatConverter implements SimpleTypeConverter {

    private static final FloatConverter INSTANCE = new FloatConverter();
    public static FloatConverter create() {
        return INSTANCE;
    }
    private FloatConverter() {
    }

    @Override
    public Object fromString(String s) {

        if(s!=null){
            return new Double(s);
        }else{
            return null;
        }
    }
    @Override
    public String toString(Object d) {
        if(d!= null){
            return d.toString();
        }else{
            return null;
        }
    }
}
