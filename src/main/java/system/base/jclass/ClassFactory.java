package system.base.jclass;

import system.base.jclass.field.IniFieldInfo;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wangchunzi
 */
final public class ClassFactory {

    private static final Map<Class, ClassInfo> map = new HashMap();

    private static ClassInfo ClassInfo(Class c) {
        IniFieldInfo iniFieldInfo = new IniFieldInfo(c);
//        System.out.println("==============================="+c.getName());
        map.put(c, iniFieldInfo.getClassInfo());
        return iniFieldInfo.getClassInfo();
    }
    
    
    //--------------------------------------------------------------------------------------

    final public static ClassInfo get(Class c) {
        ClassInfo get = map.get(c);
        return null == get ? ClassInfo(c) : get;
    }

}
