package system;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.base.jclass.ClassFactory;
import system.base.jclass.ClassInfo;
import system.base.jclass.field.FieldInfo;

/**
 *
 * @author IK
 */
public class IMYJson {

    public static <T> T jsonToObject_One(String str, Class<T> c) {
        try {
            str = str.trim().replaceAll("\n", "");
            str = str.substring(1).substring(0, str.length() - 2).trim();
            //行切割（一行等于一个对象）
            T obj;
            String value;
            String name;
            obj = c.newInstance();
            ClassInfo ci = ClassFactory.get(c);
            FieldInfo fi;
            //单元切割（一个单元等于一个属性——在对象中）
            for (String cell : str.split("[\"]{0,}[ ]{0,}[,]{1}[ ]{0,}[\"]{1}")) {
                String[] nv = cell.split("\"{1}[ ]{0,}[:]{1}[ ]{0,}\"{0,}");
                name = nv[0].startsWith("\"") ? nv[0].substring(1) : nv[0];//名
                if (nv.length == 2) {
                    value = nv[1].endsWith("\"") ? nv[1].substring(0, nv[1].length() - 1) : nv[1];//值
                } else {
                    value = "";
                }
                fi = ci.getFieldInfo(name);
                if (null != fi) {
                    fi.setValue(obj, fi.get(value));
                }
            }
            return obj;
        } catch (InstantiationException | IllegalAccessException ex) {
            return null;
        }
    }

    public static <T> List<T> jsonToObject_List(String str, Class<T> c) {
        str = str.trim().replaceAll("\n", "");
        str = str.substring(2).substring(0, str.length() - 4).trim();
        //行切割（一行等于一个对象）
        T obj;
        String value;
        String name;
        List<T> list = new ArrayList<>();
        ClassInfo ci = ClassFactory.get(c);
        FieldInfo fi;
        try {
            for (String row : str.split("[}]{1}[ ]{0,}[,]{1}[ ]{0,}[{]{1}")) {

                obj = c.newInstance();
                //单元切割（一个单元等于一个属性——在对象中）
                for (String cell : row.trim().split("[\"]{0,}[ ]{0,}[,]{1}[ ]{0,}[\"]{1}")) {
                    String[] nv = cell.split("\"{1}[ ]{0,}[:]{1}[ ]{0,}\"{0,}");
                    name = nv[0].startsWith("\"") ? nv[0].substring(1) : nv[0];//名
                    if (nv.length == 2) {
                        value = nv[1].endsWith("\"") ? nv[1].substring(0, nv[1].length() - 1) : nv[1];//值
                    } else {
                        value = "";
                    }
                    fi = ci.getFieldInfo(name);
                    if (null != fi) {
                        fi.setValue(obj, fi.get(value));
                    }
                }
                list.add(obj);
            }
            return list;
        } catch (InstantiationException | IllegalAccessException ex) {
            return null;
        }
    }
}
