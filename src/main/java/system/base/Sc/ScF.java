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

    /**
     * 找出有关Annotation类型的类
     * @param ann Annotation类型
     * @return 类集合
     */
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
    
    /**
     * 获得指定 Annotation 类型
     * @param cl 类
     * @param ann Annotation类型
     * @return 反正Annotation 类
     */
    public Annotation getMyAnnotationClass(Class cl, Class ann) {
        return cl.getAnnotation(ann);
    }

    /**
     * 返回 c类的所有有关Annotation类型的方法集合
     * @param c 类
     * @param ann Annotation类型
     * @return 集合
     */
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

    /**
     * 返回 c类的所有有关Annotation类型的属性集合
     * @param c 类
     * @param ann Annotation类型
     * @return  集合
     */
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
