package system.web.init;

import system.web.init.model.InitModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wangchunzi
 */
public class JWebInitService {

    private final system.base.log.SysLog log = new system.base.log.SysLog();
    
    public void ini(List<Class> cs) {
        log.setLogTitle("执行初始化接口实例");
        for (Class c : cs) {
            InitModel obj;
            if (InitModel.class.isAssignableFrom(c) && !c.equals(InitModel.class)) {
                try {
                    obj = (InitModel) c.newInstance();
                    obj.doInit();
                    log.putLog(1, "执行 " + c.getName());
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(JWebInitService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        log.println();
    }
}
