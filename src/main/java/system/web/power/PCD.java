package system.web.power;

import java.util.HashMap;
import java.util.Map;
import system.web.power.interfaces.UPM;

/**
 *
 * @author wangchunzi
 */
@system.web.power.ann.SQ("sasd")
public class PCD {

    private final static Map<String, String[]> urlMapPowerSortAndPowerValue= new HashMap();;
    public final static UPM UPMO;
    private static boolean hasDoOne = false;

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
