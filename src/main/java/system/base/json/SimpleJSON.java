package system.base.json;

import java.util.ArrayList;
import java.util.List;
import system.base.jclass.ClassFactory;
import system.base.jclass.ClassInfo;
import system.base.jclass.field.FieldInfo;

/**
 *
 * @author wangchunzi
 */
public class SimpleJSON {

    /**
     * 将json数据转成某对象集合(假如碰上时间，采用传参的时间格式 dateformat 或 timeformat)
     * @param <T> 泛型
     * @param c 类
     * @param jsondata      JSON数据
     * @param dateformat    日期格式
     * @param timeformat    时间格式
     * @return List 集合
     */
    final public static <T> List<T> getListBySimpleJsonData(Class<T> c, String jsondata, final String dateformat, final String timeformat) {
        jsondata = jsondata.trim().replaceAll("\n", "");
        jsondata = jsondata.substring(2).substring(0, jsondata.length() - 4).trim();
        //行切割（一行等于一个对象）
        T obj;
        String value;
        String name;
        List<T> list = new ArrayList<>();
        ClassInfo ci = ClassFactory.get(c);
        FieldInfo fi;
        try {
            for (String row : jsondata.split("[}]{1}[ ]{0,}[,]{1}[ ]{0,}[{]{1}")) {

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
                        fi.setValue(obj, fi.get(value, dateformat, timeformat));
                    }
                }
                list.add(obj);
            }
            return list;
        } catch (InstantiationException | IllegalAccessException ex) {
            return null;
        }
    }
    
    /**
     * 将json数据转成某对象集合(假如碰上时间，采用c类的属性上的 标注@Time中的值)
     * @param <T> 用户指定对象类别
     * @param c  java类
     * @param jsondata json格式的字符
     * @return 返回集合
     */
    final public static <T> List<T> getListBySimpleJsonData_CI_TIME(Class<T> c, String jsondata) {
        jsondata = jsondata.trim().replaceAll("\n", "");
        jsondata = jsondata.substring(2).substring(0, jsondata.length() - 4).trim();
        //行切割（一行等于一个对象）
        T obj;
        String value;
        String name;
        List<T> list = new ArrayList<>();
        ClassInfo ci = ClassFactory.get(c);
        FieldInfo fi;
        try {
            for (String row : jsondata.split("[}]{1}[ ]{0,}[,]{1}[ ]{0,}[{]{1}")) {

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

    /**
     * 将json数据转成一个对象(假如碰上时间，采用c类的属性上的 标注@Time中的值)
     *
     * @param <T> 泛型
     * @param c         数据对象
     * @param jsondata  json数据
     * @return T
     */
    final public static <T> T getObjectBySimpleJsonData_CI_TIME(Class<T> c, String jsondata) {
        try {
            jsondata = jsondata.trim().replaceAll("\n", "");
            jsondata = jsondata.substring(1).substring(0, jsondata.length() - 2).trim();
            //行切割（一行等于一个对象）
            T obj;
            String value;
            String name;
            obj = c.newInstance();
            ClassInfo ci = ClassFactory.get(c);
            FieldInfo fi;
            //单元切割（一个单元等于一个属性——在对象中）
            for (String cell : jsondata.split("[\"]{0,}[ ]{0,}[,]{1}[ ]{0,}[\"]{1}")) {
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

    /**
     * 将json数据转成一个对象(假如碰上时间，采用传参的时间格式 dateformat 或 timeformat)
     *
     * @param <T> 泛型
     * @param c java类
     * @param jsondata      JSON数据
     * @param dateformat    日期格式
     * @param timeformat    时间格式
     * @return T
     */
    final public static <T> T getObjectBySimpleJsonData(Class<T> c, String jsondata, final String dateformat, final String timeformat) {
        try {
            jsondata = jsondata.trim().replaceAll("\n", "");
            jsondata = jsondata.substring(1).substring(0, jsondata.length() - 2).trim();
            //行切割（一行等于一个对象）
            T obj;
            String value;
            String name;
            obj = c.newInstance();
            ClassInfo ci = ClassFactory.get(c);
            FieldInfo fi;
            //单元切割（一个单元等于一个属性——在对象中）
            for (String cell : jsondata.split("[\"]{0,}[ ]{0,}[,]{1}[ ]{0,}[\"]{1}")) {
                String[] nv = cell.split("\"{1}[ ]{0,}[:]{1}[ ]{0,}\"{0,}");
                name = nv[0].startsWith("\"") ? nv[0].substring(1) : nv[0];//名
                if (nv.length == 2) {
                    value = nv[1].endsWith("\"") ? nv[1].substring(0, nv[1].length() - 1) : nv[1];//值
                } else {
                    value = "";
                }
                fi = ci.getFieldInfo(name);
                if (null != fi) {
                    fi.setValue(obj, fi.get(value, dateformat, timeformat));
                }
            }
            return obj;
        } catch (InstantiationException | IllegalAccessException ex) {
            return null;
        }
    }

}
