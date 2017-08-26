package system.web.power;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.web.power.interfaces.IZDY;
import system.web.power.interfaces.UPM;

/**
 *
 * @author wangchunzi
 */
public class PCD {

    private final static Map<String, String[]> urlMapPowerSortAndPowerValue= new HashMap();
    public final static UPM UPMO;
    private static boolean hasDoOne = false;
    private final static Map<String,IZDY> ZDY=new HashMap();
    
    final public static IZDY getOneZDY(final String classNameKey){
        return ZDY.get(classNameKey);
    }
    public void setZDYData(final String classNameKey,Class<? extends IZDY> c) {
        if (null==ZDY.get(classNameKey)) {
            try {
                ZDY.put(classNameKey, c.newInstance());
            } catch (InstantiationException | IllegalAccessException ex) {
               System.err.print(c.getName()+":执行自定权限处理实例时出错！");
            }
        }
    }
    
    
    final public static String[] getPowerData(String url) {
        return urlMapPowerSortAndPowerValue.get(url);
    }
    
    
    
    public void setUrlAndPowerData(Map<String, String[]> m) {
        if (!hasDoOne) {
            urlMapPowerSortAndPowerValue.putAll(m);
            hasDoOne=true;
        }
    }

    static {
        UPMO = InitUPM.getUPM();
    }
}
