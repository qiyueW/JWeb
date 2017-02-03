package system.web.vo.model;

/**
 *
 * @author wangchunzi
 */
public interface VO_BeanModel {

    /**
     * 把参数装入对象中(对象属性名当参数key)
     * 如果存在时间/日期，采用全局定义的格式解析
     * @param <T>
     * @param x
     * @return 
     */
    public <T> T getObject(Class<T> x);
    /**
     * 把参数装入对象中(对象属性名当参数key)
     * @param <T>
     * @param x
     * @param dateFormat 指定日期格式
     * @param timeFormat 指定时间格式
     * @return 
     */
    public <T> T getObject(Class<T> x, final String dateFormat, final String timeFormat);
    
    /**
     * 解析参数值的json数据。并装入T实例中(通过参数key取出String型json数据)
     * 如果存在时间/日期，采用全局定义的格式解析
     * @param <T>
     * @param x
     * @param requestName 参数key
     * @return 
     */
    public <T> T getObjectByJsonData(Class<T> x, final String requestName);
    /**
     * 解析参数值的json数据。并装入T实例中(通过参数key取出String型json数据)
     * 如果存在时间/日期，采用自定义的格式解析
     * @param <T>
     * @param x
     * @param requestName 参数key
     * @param dateFormat 指定日期格式
     * @param timeFormat 指定时间格式
     * @return 
     */
    public <T> T getObjectByJsonData(Class<T> x, final String requestName, final String dateFormat, final String timeFormat);
    
}
