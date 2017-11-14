//package system.web.validate;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.regex.Pattern;
//import javax.servlet.ServletException;
//import system.base.jclass.ClassFactory;
//import system.base.jclass.ClassInfo;
//import system.base.jclass.field.FieldInfo;
//import system.web.JWeb;
//import system.web.validate.model.ValidateFieldModel;
//import system.web.validate.model.ValidateModel;
//import system.web.validate.model.ValidateResultModel;
//
///**
// *
// * @author wangchunzi
// */
//final public class ValidateEnging_JSON {
//
//    final public static boolean doValidateAndResultError(JWeb jw, ValidateModel vm) throws ServletException, IOException, IllegalArgumentException, IllegalAccessException {
//        ValidateResultModel vr = new ValidateResultModel(vm.returnJSON, vm.msg_key, vm.jsonModel);
//        String req_param = jw.getString(vm.getValidateJsonModel().getJsonKey());
//        if (req_param.startsWith("[")) {
//            List<?> list = jw.getListBySimpleJsonData(
//                    vm.getValidateJsonModel().getJsonClass(),
//                     vm.getValidateJsonModel().getJsonKey(),
//                     vm.getValidateJsonModel().getDateformat(),
//                     vm.getValidateJsonModel().getTimeformat()
//            );
//
//            ClassInfo ci = ClassFactory.get(vm.getValidateJsonModel().getJsonClass());
//            String value;
//            ValidateFieldModel vf;
//            for (Object obj : list) {
//                for (FieldInfo fi : ci.fieldInfo) {
//                    vf = vm.getValidateFieldModel().get(fi.fiel_name);
//                    if (null == vf) {
//                        continue;
//                    }
//                    value = fi.get(obj, null);
//                    if (null == value) {
//                        if (vf.isMust) {
//                            vr.put(fi.fiel_name, "为必须检验的，但不存在此参数(" + fi.fiel_name + ")");
//                            break;
//                        }
//                        continue;
//                    }
//                    if (value.isEmpty()) {
//                        if (!vf.isMust) {
//                            continue;
//                        }
//                    }
//                    if (!Pattern.compile(vf.regex).matcher(value).matches()) {
//                        vr.put(fi.fiel_name, vf.msg);
//                        break;
//                    }
//                }
//            }
//            jw.request.setAttribute(vm.getValidateJsonModel().getJsonKey(), list);
//        } else {
//            Object obj = jw.getObjectBySimpleJsonData(vm.getValidateJsonModel().getJsonClass(),
//                     vm.getValidateJsonModel().getJsonKey(),
//                     vm.getValidateJsonModel().getDateformat(),
//                     vm.getValidateJsonModel().getTimeformat());
//            ClassInfo ci = ClassFactory.get(vm.getValidateJsonModel().getJsonClass());
//            String value;
//            ValidateFieldModel vf;
//            for (FieldInfo fi : ci.fieldInfo) {
//               
//                vf = vm.getValidateFieldModel().get(fi.fiel_name);
//                if (null == vf) { //没安排校验的字段，跳过校验。
//                    continue;
//                }
//                value = fi.get(obj, null);//取出应字段的值
//                //第1种异常情况：值为null
//                if (null == value) {
//                    if (vf.isMust) {//但此参数是必须检验的
//                        //执行错误归类
//                        vr.put(fi.fiel_name, "为必须检验的，但不存在此参数(" + fi.fiel_name + ")");
//                        break;
//                    }
//                    continue;//继续检查下一个
//                }
//                //第2种异常情况：值为空。如果没有强求校验，默认通过
//                if (value.isEmpty()) {
//                    if (!vf.isMust) {
//                        continue;
//                    }
//                }
//                //字段存在检验盒中（可能要求必须检验，可能不强求，但存在校验规则），不为空，不为null，
//                //1.无强制要校验的字段，在有非空值情况下，自动执行校验
//                //2.强制要求检验的字段，开始校验。
//                if (!Pattern.compile(vf.regex).matcher(value).matches()) {
//                    vr.put(fi.fiel_name, vf.msg);
//                    break;
//                }
//            }
//            //保存校验后的对象，放到请求的属性中
//            jw.request.setAttribute(vm.getValidateJsonModel().getJsonKey(), obj);
//        }
//        //如果检查结果是错的
//        if (vr.isError()) {
//            //移除难舍难分
//            jw.request.removeAttribute(vm.getValidateJsonModel().getJsonKey());
//            vm.error(jw, vr);
//            return true;
//        }
//        //自定义复验
//        vr = vm.recheck(jw, vm.getValidateFieldModel(), vr);
//        //自定义结果是错误，
//        if (vr.isError()) {
//            //移除请求参数
//            jw.request.removeAttribute(vm.getValidateJsonModel().getJsonKey());
//            //调用自定义的错误后执行的方法
//            vm.errorRecheck(jw, vr);
//            //返回真，表示检查结果确认有错。
//            return true;
//        }
//        
//        vm.success(jw, vr);
//        return false;
//    }
//}
