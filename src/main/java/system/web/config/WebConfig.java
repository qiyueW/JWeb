package system.web.config;

/**
 *
 * @author wangchunzi
 */
public class WebConfig {

    /**
     * 请求、响应的 字符编码
     */
    public final String ENCODE;

    public final String CONTEXTPATH_KEY;

    /**
     * 默认为空时，表示拦截所有的。
     * <p>
     * 如果要拦截指定后缀的请求，请以*.开头。如：*.jw
     */
    public final String HM_SUFFIX;

    /**
     * 仅当属性HM_SUFFIX为空时，此属性才起作用。
     * <p>
     * 即：采用拦截所有的过滤方案时。
     */
    public final boolean RESOURCE_MANAGER_OPEN;

    /**
     * SQL 值注入过滤（默认开，专对传参的值进行过滤）。如果要将sql语句发给sql执行，请将此开关设置成false
     */
    public final boolean SQL_REJECT_OPEN;

    /**
     * 被转换的字符
     */
    public final String SQL_REJECT_KEY;

    /**
     * 转换后的字符
     */
    public final String SQL_REJECT_VALUE;

    /**
     * 脚本注入过滤 因为html标签很多方括号，
     * <p>
     * 为了减少替换的性能，把方括号转换改成方括号+scr的转换
     */
    public final String SCRIPT_REJECT_KEY;

    public final String SCRIPT_REJECT_VALUE;

    public final String DATE_FORMAT;

    public final String TIME_FORMAT;

    public WebConfig(system.web.config.temp.WebConfig temp) {
        this.ENCODE = temp.ENCODE;
        this.CONTEXTPATH_KEY = temp.CONTEXTPATH_KEY;
        temp.HM_SUFFIX
                = null == temp.HM_SUFFIX || temp.HM_SUFFIX.equalsIgnoreCase("*") || temp.HM_SUFFIX.startsWith("/")
                ? ""
                : temp.HM_SUFFIX.trim();

        if (temp.HM_SUFFIX.startsWith("*")) {
            temp.HM_SUFFIX = temp.HM_SUFFIX.substring(1);
        }

        this.HM_SUFFIX = temp.HM_SUFFIX;

        this.SQL_REJECT_OPEN = temp.SQL_REJECT_OPEN;
        this.SQL_REJECT_KEY = temp.SQL_REJECT_KEY;
        this.SQL_REJECT_VALUE = temp.SQL_REJECT_VALUE;

        this.SCRIPT_REJECT_KEY = temp.SCRIPT_REJECT_KEY;
        this.SCRIPT_REJECT_VALUE = temp.SCRIPT_REJECT_VALUE;
        this.RESOURCE_MANAGER_OPEN = temp.RESOURCE_MANAGER_OPEN;

        this.DATE_FORMAT = temp.DATE_FORMAT;
        this.TIME_FORMAT = temp.TIME_FORMAT;
    }

}
