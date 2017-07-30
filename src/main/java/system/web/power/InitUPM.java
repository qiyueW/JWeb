package system.web.power;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.web.init.JWebInitService;
import system.web.power.interfaces.UPM;
import system.web.power.interfaces.UPMDefault;

/**
 *
 * @author wangchunzi
 */
public class InitUPM {

    private final system.base.log.SysLog log = new system.base.log.SysLog();
    private static UPM obj = null;

    public static UPM getUPM() {
        return obj;
    }

    public InitUPM(List<Class> cs) {
        init(cs);
    }
    private void init(List<Class> cs) {
        for (Class c : cs) {
//            System.err.println(c.getName()+"//"+(UPM.class.isAssignableFrom(c) && !c.equals(UPM.class)));
            if (UPM.class.isAssignableFrom(c) && !c.equals(UPM.class)) {
                try {
                    obj = (UPM) c.newInstance();
                    log.setLogTitle("执行初始化[用户权限过滤处理]成功" + c.getName());
                    log.println();
                    return;
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(JWebInitService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        log.setLogTitle("========执行初始化[用户权限过滤处理]失败，系统自动采用默认方案============" + UPMDefault.class.getName());
        log.println();
        obj = new UPMDefault();
//        log.putLog(1, "没找到" + UPMDefault.class.getName());
//        log.println();
    }
}
