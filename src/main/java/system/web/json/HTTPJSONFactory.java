package system.web.json;

import system.web.WebContext;

/**
 *
 * @author wangchunzi
 */
final public class HTTPJSONFactory {

   final static public JsonEngineModel getJsonModel() {
        try {
            return WebContext.getWebContext().webConfig.jsonEngineModel.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            System.err.println("json 引擎初始化失败!");
            return null;
        }
    }
}
