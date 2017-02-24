package system.web.validate.config;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import system.web.JWeb;
import system.web.validate.model.ValidateFieldModel;
import system.web.validate.model.ValidateModel;
import system.web.validate.model.ValidateResultModel;

/**
 *
 * @author wangchunzi
 */
public class DefaultValidateEngine_Param extends ParamValidateEngineModel {

    @Override
    public boolean doValidateAndResultError(JWeb jw, ValidateModel vm) throws ServletException, IOException {
        ValidateResultModel vr = new ValidateResultModel(vm.returnJSON, vm.msg_key, vm.jsonModel);
        String req_param;
        for (Map.Entry<String, ValidateFieldModel> entry : vm.getValidateFieldModel().entrySet()) {
            req_param = jw.request.getParameter(entry.getKey());

            //A：如果参数不存在，但同时此参数是必须校验项时。进行异常录入
            if (null == req_param && entry.getValue().isMust) {
                vr.put(entry.getKey(), "为必须检验的，但不存在此参数的(" + entry.getKey() + ")");
            } //如果A不成立，则参数非空或非必须校验项。
            //B：设置为必须必须校验项，则A就不可能是null,所以， 不用进行对空针对的引用异常处理
            else if (entry.getValue().isMust) {
                //校验不通过
                if (!Pattern.compile(entry.getValue().regex).matcher(req_param).matches()) {
                    vr.put(entry.getValue().name, entry.getValue().msg);
                }
            } //如果A、B点都不能过，则讲明没有强制校验此参数。此参数可以是空或符合规则
            else if (null != req_param && req_param.length() != 0) {
                if (!Pattern.compile(entry.getValue().regex).matcher(req_param).matches()) {
                    vr.put(entry.getValue().name, entry.getValue().msg);
                }
            }
        }
        if (vr.isError()) {
            vm.error(jw, vr);
            return true;
        }
        vr = vm.recheck(jw, vm.getValidateFieldModel(), vr);
        if (vr.isError()) {
            vm.errorRecheck(jw, vr);
            return true;
        }
        vm.success(jw, vr);
        return false;
    }
}
