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

        return null==vm[0].getValidateJsonModel()
                ? PARAM.doValidateAndResultError(jw, vm[0])
                : JSON.doValidateAndResultError(jw, vm[0]);
    }
}
