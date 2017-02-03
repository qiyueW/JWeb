package system.web.config;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Filter启动时，初始进容器中
 *
 * @author wangchunzi
 */
public class IniWebConfig {

    public system.web.config.WebConfig getWebConfig(List<Class> list) {
        system.base.log.SysLog log = new system.base.log.SysLog();
        log.setLogTitle("加载配置文件");
        
        system.web.config.temp.WebConfig tempconfig = new system.web.config.temp.WebConfig();
        for (Class c : list) {
            if (system.web.config.temp.WebConfigModel.class.isAssignableFrom(c) && c != system.web.config.temp.WebConfigModel.class) {
                try {
                    system.web.config.temp.WebConfigModel tvm = (system.web.config.temp.WebConfigModel) c.newInstance();
                    tvm.config(tempconfig);
                    system.web.config.WebConfig wc = new system.web.config.WebConfig(tempconfig);
                    
                    log.putLog(1, "加载用户配置(" + c.getName() + ")");
                    log.println();
                    
                    return wc;
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(IniWebConfig.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        log.putLog(1, "加载默认配置(" + system.web.config.temp.WebConfig.class.getName() + ")");
        log.println();
        return new system.web.config.WebConfig(tempconfig);
    }
}
