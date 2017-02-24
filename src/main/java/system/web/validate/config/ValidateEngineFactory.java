package system.web.validate.config;

import system.web.WebContext;

/**
 *
 * @author wangchunzi
 */
public class ValidateEngineFactory {

    public  JsonValidateEngineModel getJsonValidateEngineModel() {
        try {
            return WebContext.getWebContext().webConfig.validateEngine_json.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            System.err.println("初始化检验执行器失败：" + WebContext.getWebContext().webConfig.validateEngine_json.getName());
            return null;
        }
    }

    public  ParamValidateEngineModel getParamValidateEngineModel() {
        try {
            return WebContext.getWebContext().webConfig.validateEngine_param.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            System.err.println("初始化检验执行器失败：" + WebContext.getWebContext().webConfig.validateEngine_param.getName());
            return null;
        }
    }

}
