package system.base.jclass;

import system.base.jclass.field.IniFieldInfo;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wangchunzi
 */
final public class ClassFactory {

    /**
     * 容器
     */
    private static final Map<Class, ClassInfo> MAP = new HashMap();

    /**
     * 把指定c相关的数据，放到MAP容器中，并返回相关信息（ClassInfo）
     *
     * @param c 类
     * @return ClassInfo
     */
    private static ClassInfo ClassInfo(Class c) {
        IniFieldInfo iniFieldInfo = new IniFieldInfo(c);
        MAP.put(c, iniFieldInfo.getClassInfo());
        return iniFieldInfo.getClassInfo();
    }

    /**
     * 取得某个类的相关信息 （ClassInfo）
     *
     * @param c 类
     * @return ClassInfo
     */
    final public static ClassInfo get(Class c) {
        ClassInfo get = MAP.get(c);
        return null == get ? ClassInfo(c) : get;
    }

}
