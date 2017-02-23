package system.web.json;

import java.util.List;
import system.base.json.SimpleJSON;

/**
 *
 * @author wangchunzi
 */
public class JsonEngineDefault extends JsonEngineModel {

    /**
     * 将json数据转成某对象集合(假如碰上时间，采用传参的时间格式 dateformat 或 timeformat)
     *
     * @param <T>
     * @param c
     * @param jsondata JSON数据
     * @param dateformat 日期格式
     * @param timeformat 时间格式
     * @return
     */
    @Override
    public <T> List<T> getListBySimpleJsonData(Class<T> c, String jsondata, String dateformat, String timeformat) {
        return SimpleJSON.getListBySimpleJsonData(c, jsondata, dateformat, timeformat);
    }

    /**
     * 将json数据转成某对象集合(假如碰上时间，采用c类的属性上的 标注@Time中的值)
     *
     * @param <T>
     * @param c
     * @param jsondata JSON数据
     * @return
     */
    @Override
    public <T> List<T> getListBySimpleJsonData_CI_TIME(Class<T> c, String jsondata) {
        return SimpleJSON.getListBySimpleJsonData_CI_TIME(c, jsondata);
    }

    /**
     * 将json数据转成一个对象(假如碰上时间，采用c类的属性上的 标注@Time中的值)
     *
     * @param <T>
     * @param c 数据对象
     * @param jsondata json数据
     * @return T
     */
    @Override
    public <T> T getObjectBySimpleJsonData_CI_TIME(Class<T> c, String jsondata) {
        return SimpleJSON.getObjectBySimpleJsonData_CI_TIME(c, jsondata);
    }

    /**
     * 将json数据转成一个对象(假如碰上时间，采用传参的时间格式 dateformat 或 timeformat)
     *
     * @param <T>
     * @param c
     * @param jsondata JSON数据
     * @param dateformat 日期格式
     * @param timeformat 时间格式
     * @return T
     */
    @Override
    public <T> T getObjectBySimpleJsonData(Class<T> c, String jsondata, String dateformat, String timeformat) {
        return SimpleJSON.getObjectBySimpleJsonData(c, jsondata, jsondata, jsondata);
    }

}
