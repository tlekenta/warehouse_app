package pl.edu.wat.warehouse_app.util.converter;

import net.sf.jsefa.common.converter.SimpleTypeConverter;

import java.sql.Timestamp;

public class TimeStampConverter implements SimpleTypeConverter {

    private static final TimeStampConverter INSTANCE = new TimeStampConverter();
    public static TimeStampConverter create() {
        return INSTANCE;
    }
    private TimeStampConverter() {
    }

    @Override
    public Object fromString(String s) {
        if(s!=null){
            int year = Integer.parseInt(s.substring(6,10));
            int month = Integer.parseInt(s.substring(3,5));
            int day = Integer.parseInt(s.substring(0,2));
            return new Timestamp(year, month, day,0,0,0,0);
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
