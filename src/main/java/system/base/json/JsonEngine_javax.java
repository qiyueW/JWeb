//package system.base.json;
//
//import java.io.StringReader;
//import java.util.ArrayList;
//import java.util.List;
//import javax.json.Json;
//import javax.json.JsonArray;
//import javax.json.JsonObject;
//import javax.json.JsonReader;
//import system.base.jclass.ClassFactory;
//import system.base.jclass.ClassInfo;
//import system.base.jclass.field.FieldInfo;
//
///**
// *
// * @author wangchunzi
// */
//final public class JsonEngine_javax {
//
//    final public static <T> List<T> getListBySimpleJsonData(Class<T> x, final String jsondata, final String dateformat, final String timeformat) {
//        JsonReader jr = Json.createReader(new StringReader(jsondata));
//        JsonArray jsonArr = jr.readArray();
//        jr.close();
//        ClassInfo ci = ClassFactory.get(x);
//        List<T> list = new ArrayList<>();
//        T obj;
//        JsonObject jobj;
//        try {
//            for (int i = 0; i < jsonArr.size(); i++) {
//                jobj = jsonArr.getJsonObject(i);
//                obj = x.newInstance();
//                for (FieldInfo fi : ci.fieldInfo) {
//                    fi.setValue(obj, fi.get(jobj.getString(fi.fiel_name), dateformat, timeformat));
//                }
//                list.add(obj);
//            }
//        } catch (IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
//            return new ArrayList<>();
//        }
//        return list;
//    }
//
//    final public static <T> List<T> getListBySimpleJsonData(Class<T> x, final String jsondata) {
//        JsonReader jr = Json.createReader(new StringReader(jsondata));
//        JsonArray jsonArr = jr.readArray();
//        jr.close();
//        ClassInfo ci = ClassFactory.get(x);
//        List<T> list = new ArrayList<>();
//        T obj;
//        JsonObject jobj;
//        try {
//            for (int i = 0; i < jsonArr.size(); i++) {
//                jobj = jsonArr.getJsonObject(i);
//                obj = x.newInstance();
//                for (FieldInfo fi : ci.fieldInfo) {
//                    fi.setValue(obj, fi.get(jobj.getString(fi.fiel_name)));
//                }
//                list.add(obj);
//            }
//        } catch (IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
//            return new ArrayList<>();
//        }
//        return list;
//    }
//
//    final public static <T> T getObjectBySimpleJsonData(Class<T> x, final String jsondata) {
//        JsonReader jr = Json.createReader(new StringReader(jsondata));
//        JsonObject jobj = jr.readObject();
//        jr.close();
//        ClassInfo ci = ClassFactory.get(x);
//        T obj;
//        try {
//            obj = x.newInstance();
//        } catch (InstantiationException | IllegalAccessException ex) {
//            return null;
//        }
//        for (FieldInfo fi : ci.fieldInfo) {
//            fi.setValue(obj, fi.get(jobj.getString(fi.fiel_name)));
//        }
//        return obj;
//    }
//
//    final public static <T> T getObjectBySimpleJsonData(Class<T> x, final String jsondata, final String dateformat, final String timeformat) {
//        JsonReader jr = Json.createReader(new StringReader(jsondata));
//        JsonObject jobj = jr.readObject();
//        jr.close();
//        ClassInfo ci = ClassFactory.get(x);
//        T obj;
//        try {
//            obj = x.newInstance();
//        } catch (InstantiationException | IllegalAccessException ex) {
//            return null;
//        }
//        for (FieldInfo fi : ci.fieldInfo) {
//            fi.setValue(obj, fi.get(jobj.getString(fi.fiel_name), dateformat, timeformat));
//        }
//        return obj;
//    }
//
//}
