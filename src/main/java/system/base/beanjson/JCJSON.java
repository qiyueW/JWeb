package system.base.beanjson;

import java.util.List;
import system.base.jclass.ClassFactory;
import system.base.jclass.ClassInfo;

/**
 *
 * @author wangchunzi
 */
final public class JCJSON {

    /**
     * 处理简单对象成json格式的字符串
     * @param <T> 泛型
     * @param t 泛型实例
     * @return  json格式的字符串
     */
    final public static <T> String toSimpleJSON(T t) {
        if (null == t) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        ClassInfo ci = ClassFactory.get(t.getClass());
        sb.append("{\"").append(ci.fieldInfo[0].fiel_name).append("\":\"").append(ci.fieldInfo[0].get(t, "")).append("\"");
        for (int i = 1; i < ci.fieldInfo.length; i++) {
            sb.append(",\"").append(ci.fieldInfo[i].fiel_name).append("\":\"").append(ci.fieldInfo[i].get(t, "")).append("\"");
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * 
     * 处理简单对象集合成json格式的字符串
     * @param <T> 泛型
     * @param list 泛型实例的集合
     * @return  json格式的字符串
     */
    final public static <T> String toSimpleJSON(List<T> list) {
        if (null == list || list.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        ClassInfo ci = ClassFactory.get(list.get(0).getClass());
        
        for (T obj : list) {
            sb.append(",{\"").append(ci.fieldInfo[0].fiel_name).append("\":\"").append(ci.fieldInfo[0].get(obj, "")).append("\"");
            for (int i = 1; i < ci.fieldInfo.length; i++) {
                sb.append(",\"").append(ci.fieldInfo[i].fiel_name).append("\":\"").append(ci.fieldInfo[i].get(obj, "").replace("\"","\\\"")).append("\"");
            }
            sb.append("}");
        }
        return "[" + sb.substring(1) + "]";
    }
}
