//package system.web.param.model;
//
//import java.lang.reflect.Field;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// *
// * @author wangchunzi
// */
//public abstract class VO_Father {
//
//    private static final Map<Class, Field[]> map = new HashMap();
//    
//    protected static Field[] getBeanModel(Class c) {
//        Field[] f = map.get(c);
//        return null == f ? iniBeanModel(c) : f;
//    }
//
//    private static Field[] iniBeanModel(Class c) {
//        Field[] f = c.getDeclaredFields();
//        for (Field obj : f) {
//            obj.setAccessible(true);
//        }
//        map.put(c, f);
//        return f;
//    }
//
//}
