package system.web.power;

import java.util.HashMap;
import java.util.Map;
import system.web.power.interfaces.UPM;

/**
 *
 * @author wangchunzi
 */
@system.web.power.ann.SQ(value = "sasd", scope = PDK.SESSION_DEFAULT_KEY)
public class PCD {

    private final static Map<String, String[]> UrlAndPowerData= new HashMap();;
    public final static UPM UPMO;
    private static boolean hasDoOne = false;

    final public static String[] getPowerData(String url) {
        return UrlAndPowerData.get(url);
    }

    
    
    public void setUrlAndPowerData(Map<String, String[]> m) {
        if (!hasDoOne) {
            UrlAndPowerData.putAll(m);
            hasDoOne=true;
        }
    }

    static {
        UPMO = InitUPM.getUPM();
    }
}
