package system.web;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局容器
 *
 * @author wangchunzi
 */
public final class WebContext {

    public final String WEB_INF_PATH;
    public final String WEB_PATH;
    public final String ContextPath;
    public final boolean islinux;
    public final Map<Object, Object> map;
    public final system.web.config.WebConfig webConfig;
    private static boolean one = true;
    private static WebContext webcontext;

    public static WebContext getWebContext() {
        return webcontext;
    }

    public static void setWebContext(WebContext x) {
        if (one) {
            webcontext = x;
            one = false;
        }
    }

    public WebContext(String ContextPath, String root_path, String WEB_INF_PATH, boolean islinux, system.web.config.WebConfig config) {
        this.map = new HashMap();
        this.WEB_INF_PATH = WEB_INF_PATH;
        this.WEB_PATH = root_path;
        this.islinux = islinux;
        this.ContextPath = ContextPath;
        this.webConfig = config;
    }

    public Object get(String key) {
        return map.get(key);
    }

    public void set(String key, Object obj) {
        map.put(key, obj);
    }

    public <T> T get(Class<T> t, String key) {
        return (T) map.get(key);
    }

}
