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

    public static boolean doValidateAndResultError(JWeb jw, ValidateModel[] vm) throws ServletException, IOException, IllegalArgumentException, IllegalAccessException {
        switch (vm.length) {
            case 0:
                return true;//没有检查类，却进入检查指定。直接返回错误
            case 1:
                return null == vm[0].getValidateJsonModel() ? PARAM.doValidateAndResultError(jw, vm[0]) : JSON.doValidateAndResultError(jw, vm[0]);
            case 2:
                //第1个为错（true时），或第2个为错时（true时）
                return null == vm[0].getValidateJsonModel() ? PARAM.doValidateAndResultError(jw, vm[0]) : JSON.doValidateAndResultError(jw, vm[0])
                        || null == vm[1].getValidateJsonModel() ? PARAM.doValidateAndResultError(jw, vm[1]) : JSON.doValidateAndResultError(jw, vm[1]);
            case 3:
                //第1个为错（true时），或第2个为错时（true时）或第3个为错时（true时）
                return null == vm[0].getValidateJsonModel() ? PARAM.doValidateAndResultError(jw, vm[0]) : JSON.doValidateAndResultError(jw, vm[0])
                        || null == vm[1].getValidateJsonModel() ? PARAM.doValidateAndResultError(jw, vm[1]) : JSON.doValidateAndResultError(jw, vm[1])
                        || null == vm[2].getValidateJsonModel() ? PARAM.doValidateAndResultError(jw, vm[2]) : JSON.doValidateAndResultError(jw, vm[2]);
            default: {
                for (ValidateModel vobj : vm) {
                    if (null == vobj.getValidateJsonModel() ? PARAM.doValidateAndResultError(jw, vobj) : JSON.doValidateAndResultError(jw, vobj)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }
}
