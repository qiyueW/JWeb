package system.base.properties;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.base.jclass.ClassTypeCode;
import static system.base.jclass.ClassTypeCode.BOOLEAN;
import static system.base.jclass.ClassTypeCode.BYTE;
import static system.base.jclass.ClassTypeCode.DATE;
import static system.base.jclass.ClassTypeCode.DOUBLE;
import static system.base.jclass.ClassTypeCode.FLOAT;
import static system.base.jclass.ClassTypeCode.INTEGER;
import static system.base.jclass.ClassTypeCode.LONG;
import static system.base.jclass.ClassTypeCode.SHORT;
import static system.base.jclass.ClassTypeCode.STRING;

/**
 *
 * @author wangchunzi
 */
final public class PropertiesFactory {

    private final PropertiesConfig pc;
    private final Object obj;

    public PropertiesFactory(PropertiesConfig pc) {
        this.pc = pc;
        obj = this.iniProperties();
    }

    public Object get() {
        return this.obj;
    }

    private Object iniProperties() {
        Object myobj = null;
        try {
            myobj = pc.c.newInstance();
            Object value;
            Properties rb = new Properties();
            System.out.println(PropertiesFactory.class.getClassLoader().getResource("").toString());
            InputStream is = PropertiesFactory.class.getClassLoader().getResourceAsStream(pc.filepath);

            if (null == is) {
                System.err.println("找不到文件。请检查配置!");
                return null;
            }
            rb.load(is);
            is.close();
            for (Field f : pc.c.getDeclaredFields()) {
                value = rb.get(f.getName());
                if (null != value) {
                    f.setAccessible(true);
                   this.set(f,myobj, value.toString());
                }
            }
            return myobj;
        } catch (InstantiationException | IllegalAccessException | IOException ex) {
            Logger.getLogger(PropertiesFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myobj;
    }

    private void set(Field f, Object obj, String v) throws IllegalArgumentException, IllegalAccessException {
        switch (ClassTypeCode.getTypeCode(f.getType())) {
            case STRING:
                f.set(obj, v);
                return;
            case SHORT:
                f.set(obj, Short.parseShort(v));
                return;
            case INTEGER:
                f.set(obj, Integer.parseInt(v));
                return;
            case LONG:
                f.set(obj, Long.parseLong(v));
                return;
            case FLOAT:
                f.set(obj, Float.parseFloat(v));
                return;
            case DOUBLE:
                f.set(obj, Double.parseDouble(v));
                return;
            case BOOLEAN:
                f.set(obj, Boolean.parseBoolean(v));
        }
    }
}
