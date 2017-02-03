package system.base.Sc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IK
 */
public class ScF extends Sc {

    public List<Class> getMyClass_X(Class ann) {
        List<Class> list = new ArrayList<>();
        for (Class c : cs) {
            if (null != c.getAnnotation(ann)) {
                list.add(c);
            }
        }
        return list;
    }

    public List<Class> getMyClass() {
        return cs;
    }
    
    public Annotation getMyAnnotationClass(Class cl, Class ann) {
        return cl.getAnnotation(ann);
    }

    public List getMyAnnotationMehtod(Class c, Class ann) {
        List list = new ArrayList<>();
        Annotation a;
        for (Method m : c.getMethods()) {
            a = m.getAnnotation(ann);
            if (null != a) {
                list.add(a);
            }
        }
        return list;
    }

    public List getMyAnnotationFiled(Class c, Class ann) {
        List list = new ArrayList<>();
        Annotation a;
        for (Field f : c.getDeclaredFields()) {
            a = f.getAnnotation(ann);
            if (null != a) {
                list.add(a);
            }
        }
        return list;
    }

}
