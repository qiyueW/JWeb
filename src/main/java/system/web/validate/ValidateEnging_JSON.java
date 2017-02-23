package system.web.validate;

import java.io.IOException;
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
final public class ValidateEnging_JSON {

    public static boolean doValidateAndResultError(JWeb jw, ValidateModel vm) throws ServletException, IOException, IllegalArgumentException, IllegalAccessException {
        ValidateResultModel vr = new ValidateResultModel(vm.returnJSON, vm.msg_key, vm.jsonModel);
        String req_param = jw.getString(vm.getJSON_KEY());
        if (req_param.startsWith("[")) {
            List<?> list = jw.getListBySimpleJsonData(vm.getJSON_Class(), vm.getJSON_KEY());
            ClassInfo ci = ClassFactory.get(vm.getJSON_Class());
            String value;
            ValidateFieldModel vf;
            for (Object obj : list) {
                for (FieldInfo fi : ci.fieldInfo) {
                    vf = vm.getValidateFieldModel().get(fi.fiel_name);
                    if(null==vf)continue;
                    value = fi.get(obj, null);
                    if (null == value) {
                        if (vf.isMust) {
                            vr.put(fi.fiel_name, "为必须检验的，但不存在此参数(" + fi.fiel_name + ")");
                            continue;
                        }
                        continue;
                    }
                    if (value.isEmpty()) {
                        if (!vf.isMust) {
                            continue;
                        }
                    }
                    if (!Pattern.compile(vf.regex).matcher(value).matches()) {
                        vr.put(fi.fiel_name, vf.msg);
                    }
                }
            }
            jw.request.setAttribute(vm.getJSON_KEY(), list);
        } else {
            Object obj = jw.getObjectBySimpleJsonData(vm.getJSON_Class(), vm.getJSON_KEY());
            ClassInfo ci = ClassFactory.get(vm.getJSON_Class());
            String value;
            ValidateFieldModel vf;
            for (FieldInfo fi : ci.fieldInfo) {
                vf = vm.getValidateFieldModel().get(fi.fiel_name);
                if(null==vf)continue;
                value = fi.get(obj, null);
                if (null == value) {
                    if (vf.isMust) {
                        vr.put(fi.fiel_name, "为必须检验的，但不存在此参数(" + fi.fiel_name + ")");
                        continue;
                    }
                    continue;
                }
                if (value.isEmpty()) {
                    if (!vf.isMust) {
                        continue;
                    }
                }
                if (!Pattern.compile(vf.regex).matcher(value).matches()) {
                    vr.put(fi.fiel_name, vf.msg);
                }
            }
            jw.request.setAttribute(vm.getJSON_KEY(), obj);
        }
        if (vr.isError()) {
            jw.request.removeAttribute(vm.getJSON_KEY());
            vm.error(jw, vr);
            return true;
        }
        vr = vm.recheck(jw, vm.getValidateFieldModel(), vr);
        if (vr.isError()) {
            jw.request.removeAttribute(vm.getJSON_KEY());
            vm.errorRecheck(jw, vr);
            return true;
        }

        vm.success(jw, vr);
        return false;
    }
}
