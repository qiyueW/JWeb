package system.web.power;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import system.web.power.data.IPowerData;
import system.web.power.data.PD;
import system.web.power.data.PowerComparator;

/**
 *
 * @author wangchunzi
 */
public class InitIPD {

    private final system.base.log.SysLog log = new system.base.log.SysLog();

    public InitIPD(List<Class> cs) {
        init(cs);
    }
    private void init(List<Class> cs) {
        List<IPowerData> list=new ArrayList<>();
        IPowerData obj;
        for (Class c : cs) {
//            System.err.println(c.getName()+"//"+(UPM.class.isAssignableFrom(c) && !c.equals(UPM.class)));
            if (IPowerData.class.isAssignableFrom(c) && !c.equals(IPowerData.class)) {
                try {
                    obj = (IPowerData) c.newInstance();
                    list.add(obj);
                } catch (InstantiationException | IllegalAccessException ex) {
                    System.err.println("实例权限类数据类时，发生错误，权限管理中止！！");
                    return;
                }
            }
        }
        if(list.isEmpty())return;
        
        log.setLogTitle("【用户权限数据设定]】开始......");
        PD pdt;
        Collections.sort(list, new PowerComparator());
        for(IPowerData pd:list){
            pdt=new PD();
            pd.doPowerData(pdt);
            pdt.initEndModelDo_setKeyMapModule(pd.key());
            log.putLog(1, "执行"+pd.getClass().getName());
        }
        log.setLogTitle("【用户权限数据设定】结束.....");
        log.println();
    }
}
