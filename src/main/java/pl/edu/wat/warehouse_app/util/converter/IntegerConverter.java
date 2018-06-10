package pl.edu.wat.warehouse_app.util.converter;

import net.sf.jsefa.common.converter.SimpleTypeConverter;

public class IntegerConverter implements SimpleTypeConverter {

    private static final IntegerConverter INSTANCE = new IntegerConverter();
    public static IntegerConverter create() {
        return INSTANCE;
    }
    private IntegerConverter() {
    }

    @Override
    public Object fromString(String s) {
        if(s!=null){
            String st = s.replaceAll("\\D+","");
            return Integer.parseInt(st);
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
