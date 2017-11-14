package system.web.json;

import java.util.List;

/**
 *
 * @author wangchunzi
 */
public abstract class JsonEngineModel {

    /**
     * 将json数据转成某对象集合(假如碰上时间，采用传参的时间格式 dateformat 或 timeformat)
     *
     * @param <T>
     * @param x
     * @param jsondata JSON数据
     * @param dateformat 日期格式
     * @param timeformat 时间格式
     * @return
     */
    abstract public <T> List<T> getListBySimpleJsonData(Class<T> x, final String jsondata, final String dateformat, final String timeformat);

    /**
     * 将json数据转成某对象集合(假如碰上时间，采用c类的属性上的 标注@Time中的值)
     *
     * @param <T>
     * @param x
     * @param jsondata JSON数据
     * @return
     */
    abstract public <T> List<T> getListBySimpleJsonData_CI_TIME(Class<T> x, final String jsondata);

    /**
     * 将json数据转成一个对象(假如碰上时间，采用c类的属性上的 标注@Time中的值)
     *
     * @param <T>
     * @param x 数据对象
     * @param jsondata json数据
     * @return T
     */
    abstract public <T> T getObjectBySimpleJsonData_CI_TIME(Class<T> x, final String jsondata);

    /**
     * 将json数据转成一个对象(假如碰上时间，采用传参的时间格式 dateformat 或 timeformat)
     *
     * @param <T>
     * @param x
     * @param jsondata JSON数据
     * @param dateformat 日期格式
     * @param timeformat 时间格式
     * @return T
     */
    abstract public <T> T getObjectBySimpleJsonData(Class<T> x, final String jsondata, final String dateformat, final String timeformat);
}
