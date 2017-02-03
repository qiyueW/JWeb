package system.web.filter.resource;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wangchunzi
 */
public class ResourceService {

    public ConfigurationResource getConfigurationResource(List<Class> cs) {
        ConfigurationResource cr;
        system.base.log.SysLog log = new system.base.log.SysLog();
        log.setLogTitle("初始化过滤资源执行对象");
        for (Class c : cs) {
            if (ConfigurationResource.class.isAssignableFrom(c)
                    && c != DefaultResource.class
                    && c != ConfigurationResource.class) {
                try {
                    cr = (ConfigurationResource) c.newInstance();
                    log.putLog(1, "找到用户资源处理类： " + c.getName());
                    log.println();
                    return cr;
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(ResourceService.class.getName()).log(Level.SEVERE, null, ex);
                    //实例过滤静态资源处理对象时发生错误。
                }
            }
        }
        DefaultResource defaultResource = new DefaultResource();
        log.putLog(1, "没找到用户资源处理类，系统将采用默认资源处理类： " + DefaultResource.class.getName());
        log.putLog(1, "注:你目前采用的是默认方案:拦截所有的请求。");
        log.putLog(1, " (1)如果需要拦截指定后缀的URL，请继承"
                + system.web.config.temp.WebConfigModel.class + ",并在实现抽象方法的时候，对传参的属性：HM_SUFFIX进行赋值(注：需以*.开头)。");
        log.putLog(1, " (2)如果需要自定处理资源类，请继承" + ConfigurationResource.class.getName() + ",并实现其中的抽象方法");
        log.println();
        return defaultResource;
    }

}
