package system.web.hm.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wangchunzi
 */
final public class LinkHMData {

    private static final Map<String, LinkHMModel> requestURLMap = new HashMap();
    private static boolean open = true;

    //-------------------初始化数据---------------------------------
    public void initPutHMModel(String url, LinkHMModel o) {
        if (open) {
            requestURLMap.put(url, o);
            System.out.println("单例化一个连接类" + url + "// method:" + o.hClass.getName() + "//" + o.method.getName() + "// istatic:" + o.isStaticMethod);
        }
    }

    //-------------------完成初始化数据---------------------------------
    public void iniOver() {
        open = false;
    }

    /**
     * 加入黑名单 从此此URL请求不再可用。直到服务器重启
     *
     * @param url
     */
    public static void addBlacklist(String url) {
        requestURLMap.remove(url);
    }
    
    final public static LinkHMModel getLinkModel(final String servletURL) {
        return requestURLMap.get(servletURL);
    }

}
