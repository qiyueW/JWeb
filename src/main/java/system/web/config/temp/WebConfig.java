package system.web.config.temp;

/**
 *
 * @author wangchunzi
 */
public class WebConfig {

    /**
     * 请求、响应的 字符编码
     */
    public String ENCODE = "UTF-8";

    public String CONTEXTPATH_KEY = "path_home";

    /**
     * 默认为空时，表示拦截所有的。
     * <p>
     * 如果要拦截指定后缀的请求，请以*.开头。如：*.jw
     */
    public String HM_SUFFIX = "";

    /**
     * 仅当属性HM_SUFFIX为空时，此属性才起作用。
     * <p>
     * 即：采用拦截所有的过滤方案时。
     */
    public boolean RESOURCE_MANAGER_OPEN = true;

    /**
     * SQL 值注入过滤（默认开，专对传参的值进行过滤）。如果要将sql语句发给sql执行，请将此开关设置成false
     */
    public boolean SQL_REJECT_OPEN = true;

    /**
     * 被转换的字符
     */
    public String SQL_REJECT_KEY = "'";

    /**
     * 转换后的字符
     */
    public String SQL_REJECT_VALUE = "&#39;";

    /**
     * 脚本注入过滤 因为html标签很多方括号，
     * <p>
     * 为了减少替换的性能，把方括号转换改成方括号+scr的转换
     */
    public String SCRIPT_REJECT_KEY = "<scr";

    public String SCRIPT_REJECT_VALUE = "&#60;scr";
    
    public String DATE_FORMAT = "yyyy-MM-dd";
    
    public String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
}
