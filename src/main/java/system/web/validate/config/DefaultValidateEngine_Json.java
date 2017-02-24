package system.web.validate.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import system.base.jclass.ClassFactory;
import system.base.jclass.ClassInfo;
import system.base.jclass.field.FieldInfo;
import system.web.JWeb;
import system.web.validate.model.ValidateFieldModel;
import system.web.validate.model.ValidateModel;
import system.web.validate.model.ValidateResultModel;

/**
 *
 * @author wangchunzi
 */
final public class DefaultValidateEngine_Json extends JsonValidateEngineModel {

    @Override
    final public boolean doValidateAndResultError(JWeb jw, ValidateModel vm) throws ServletException, IOException, IllegalArgumentException, IllegalAccessException {
        String jsondata = jw.getString(vm.getValidateJsonModel().getJsonKey());
        if (null == jsondata || jsondata.length() < 5) {
            return true;
        }
        ValidateResultModel vr = new ValidateResultModel(vm.returnJSON, vm.msg_key, vm.jsonModel);
        jsondata = jsondata.trim().replaceAll("\n", "");
        int mustcount = 0;
        int sdmustcount = vm.getMustValidateFieldCount();
        int resultSDmustcount = 0;
        Object obj;
        String value;
        String name;
        Class c = vm.getValidateJsonModel().getJsonClass();
        ClassInfo ci = ClassFactory.get(c);
        ValidateFieldModel vf;
        FieldInfo fi;

        if (jsondata.startsWith("[")) {
            jsondata = jsondata.substring(2).substring(0, jsondata.length() - 4).trim();
            //行切割（一行等于一个对象）
            List list = new ArrayList<>();
            try {
                for (String row : jsondata.split("[}]{1}[ ]{0,}[,]{1}[ ]{0,}[{]{1}")) {
                    obj = c.newInstance();
                    //单元切割（一个单元等于一个属性——在对象中）
                    resultSDmustcount = resultSDmustcount + sdmustcount;
                    for (String cell : row.trim().split("[\"]{0,}[ ]{0,}[,]{1}[ ]{0,}[\"]{1}")) {
                        String[] nv = cell.split("\"{1}[ ]{0,}[:]{1}[ ]{0,}\"{0,}");
                        name = nv[0].startsWith("\"") ? nv[0].substring(1) : nv[0];//名
                        vf = vm.getValidateFieldModel().get(name);
                        value = nv.length == 2 ? (nv[1].endsWith("\"") ? nv[1].substring(0, nv[1].length() - 1) : nv[1]) : "";
                        fi = ci.getFieldInfo(name);

                        //有值，但不存在检验项
                        if (null == vf) {
                            if (null != fi) {//不为null,是指定对象属性的值
                                fi.setValue(obj, fi.get(value, vm.getValidateJsonModel().getDateformat(), vm.getValidateJsonModel().getTimeformat()));
                            }
                            continue;
                        }
                        //如果值为空
                        if (value.isEmpty()) {
                            if (!vf.isMust) {//检查是否为必须检查，如果不是，则继续进行循环
                                if (null != fi && fi.fiel_type == String.class) {
                                    fi.setValue(obj, "");
                                }
                                continue;
                            }
                        }
                        //有值，进行检查。如果不通过，直接中止循环。
                        if (!Pattern.compile(vf.regex).matcher(value).matches()) {
                            vr.put(name, vf.msg);
                            break;
                        }
                        if (vf.isMust) {
                            mustcount++;//必须检验的值
                        }
                        if (null != fi) {//不为null,是指定对象属性的值
                            fi.setValue(obj, fi.get(value, vm.getValidateJsonModel().getDateformat(), vm.getValidateJsonModel().getTimeformat()));
                        }
                    }
                    list.add(obj);
                }

            } catch (InstantiationException ex) {
                list.clear();
                return true;
            }
            jw.request.setAttribute(vm.getValidateJsonModel().getJsonKey(), list);
        } else {
            try {
                //行切割（一行等于一个对象）
                jsondata = jsondata.substring(1).substring(0, jsondata.length() - 2).trim();
                obj = c.newInstance();
                //单元切割（一个单元等于一个属性——在对象中）
                sdmustcount++;
                for (String cell : jsondata.split("[\"]{0,}[ ]{0,}[,]{1}[ ]{0,}[\"]{1}")) {
                    String[] nv = cell.split("\"{1}[ ]{0,}[:]{1}[ ]{0,}\"{0,}");
                    name = nv[0].startsWith("\"") ? nv[0].substring(1) : nv[0];//名
                    value = nv.length == 2 ? (nv[1].endsWith("\"") ? nv[1].substring(0, nv[1].length() - 1) : nv[1]) : "";
                    vf = vm.getValidateFieldModel().get(name);
                    fi = ci.getFieldInfo(name);
                    //有值，但不存在检验项
                    if (null == vf) {
                        if (null != fi) {//不为null,是指定对象属性的值
                            fi.setValue(obj, fi.get(value, vm.getValidateJsonModel().getDateformat(), vm.getValidateJsonModel().getTimeformat()));
                        }
                        continue;
                    }
                    //如果值为空
                    if (value.isEmpty()) {
                        if (!vf.isMust) {//检查是否为必须检查，如果不是，则继续进行循环
                            if (null != fi && fi.fiel_type == String.class) {
                                fi.setValue(obj, "");
                            }
                            continue;
                        }
                    }
                    //有值，进行检查。如果不通过，直接中止循环。
                    if (!Pattern.compile(vf.regex).matcher(value).matches()) {
                        vr.put(name, vf.msg);
                        break;
                    }
                    if (vf.isMust) {
                        mustcount++;//必须检验的值
                    }
                    if (null != fi) {//不为null,是指定对象属性的值
                        fi.setValue(obj, fi.get(value, vm.getValidateJsonModel().getDateformat(), vm.getValidateJsonModel().getTimeformat()));
                    }
                }
                jw.request.setAttribute(vm.getValidateJsonModel().getJsonKey(), obj);
            } catch (InstantiationException ex) {
                Logger.getLogger(DefaultValidateEngine_Json.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (mustcount != resultSDmustcount) {
            vr.put(vm.getValidateJsonModel().getCountIsError_MessageKEY(), vm.getValidateJsonModel().getCountIsError_Message());
        }
        if (vr.isError()) {
            jw.request.removeAttribute(vm.getValidateJsonModel().getJsonKey());
            vm.error(jw, vr);
            return true;
        }
        vr = vm.recheck(jw, vm.getValidateFieldModel(), vr);
        if (vr.isError()) {
            jw.request.removeAttribute(vm.getValidateJsonModel().getJsonKey());
            vm.errorRecheck(jw, vr);
            return true;
        }

        vm.success(jw, vr);
        return false;
    }

}
