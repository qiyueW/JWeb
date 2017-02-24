package system.web.validate;

import java.io.IOException;
import javax.servlet.ServletException;
import system.web.JWeb;
import system.web.validate.config.JsonValidateEngineModel;
import system.web.validate.config.ParamValidateEngineModel;
import system.web.validate.config.ValidateEngineFactory;
import system.web.validate.model.ValidateModel;

/**
 *
 * @author wangchunzi
 */
final public class ValidateEnging {

    private static final JsonValidateEngineModel JSON;
    private static final ParamValidateEngineModel PARAM;

    static {
        ValidateEngineFactory vef = new ValidateEngineFactory();
        JSON = vef.getJsonValidateEngineModel();
        PARAM = vef.getParamValidateEngineModel();
    }

    public static boolean doValidateAndResultError(JWeb jw, ValidateModel vm) throws ServletException, IOException, IllegalArgumentException, IllegalAccessException {

        return null==vm.getValidateJsonModel()
                ? PARAM.doValidateAndResultError(jw, vm)
                : JSON.doValidateAndResultError(jw, vm);

//        if (vm.isJSONEngine()) {
        //            try {
        //                return ValidateEnging_JSON.doValidateAndResultError(jw, vm);
        //            } catch (IllegalArgumentException | IllegalAccessException ex) {
        //               return true;
        //            }
        //        }
        //        ValidateResultModel vr = new ValidateResultModel(vm.returnJSON, vm.msg_key, vm.jsonModel);
        //        String req_param;
        //        for (Map.Entry<String, ValidateFieldModel> entry : vm.getValidateFieldModel().entrySet()) {
        //            req_param = jw.request.getParameter(entry.getKey());
        //
        //            //A：如果参数不存在，但同时此参数是必须校验项时。进行异常录入
        //            if (null == req_param && entry.getValue().isMust) {
        //                vr.put(entry.getKey(), "为必须检验的，但不存在此参数的(" + entry.getKey() + ")");
        //            } //如果A不成立，则参数非空或非必须校验项。
        //            //B：设置为必须必须校验项，则A就不可能是null,所以， 不用进行对空针对的引用异常处理
        //            else if (entry.getValue().isMust) {
        //                //校验不通过
        //                if (!Pattern.compile(entry.getValue().regex).matcher(req_param).matches()) {
        //                    vr.put(entry.getValue().name, entry.getValue().msg);
        //                }
        //            } //如果A、B点都不能过，则讲明没有强制校验此参数。此参数可以是空或符合规则
        //            else if (null != req_param && req_param.length() != 0) {
        //                if (!Pattern.compile(entry.getValue().regex).matcher(req_param).matches()) {
        //                    vr.put(entry.getValue().name, entry.getValue().msg);
        //                }
        //            }
        //        }
        //        if (vr.isError()) {
        //            vm.error(jw, vr);
        //            return true;
        //        }
        //        vr = vm.recheck(jw, vm.getValidateFieldModel(), vr);
        //        if (vr.isError()) {
        //            vm.errorRecheck(jw, vr);
        //            return true;
        //        }
        //        vm.success(jw, vr);
        //        return false;
    }
}
