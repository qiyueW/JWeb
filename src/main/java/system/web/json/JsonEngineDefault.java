package system.web.json;

import java.util.List;
import system.base.json.JsonEngine_javax;

/**
 *
 * @author wangchunzi
 */
public class JsonEngineDefault extends JsonEngineModel {

    @Override
    public <T> List<T> getListBySimpleJsonData(Class<T> x, String jsondata, String dateformat, String timeformat) {
        return JsonEngine_javax.getListBySimpleJsonData(x, jsondata, dateformat, timeformat);
    }

    @Override
    public <T> List<T> getListBySimpleJsonData(Class<T> x, String jsondata) {
        return JsonEngine_javax.getListBySimpleJsonData(x, jsondata);
    }

    @Override
    public <T> T getObjectBySimpleJsonData(Class<T> x, String jsondata) {
        return JsonEngine_javax.getObjectBySimpleJsonData(x, jsondata, jsondata, jsondata);
    }
    
    @Override
    public <T> T getObjectBySimpleJsonData(Class<T> x, String jsondata, String dateformat, String timeformat) {
        return JsonEngine_javax.getObjectBySimpleJsonData(x, jsondata, jsondata, jsondata);
    }

}
