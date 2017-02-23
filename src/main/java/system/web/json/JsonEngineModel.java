package system.web.json;

import java.util.List;

/**
 *
 * @author wangchunzi
 */
public abstract class JsonEngineModel {

    abstract public <T> List<T> getListBySimpleJsonData(Class<T> x, final String jsondata, final String dateformat, final String timeformat);

    abstract public <T> List<T> getListBySimpleJsonData(Class<T> x, final String jsondata);

    abstract public <T> T getObjectBySimpleJsonData(Class<T> x, final String jsondata);

    abstract public <T> T getObjectBySimpleJsonData(Class<T> x, final String jsondata, final String dateformat, final String timeformat);
}
