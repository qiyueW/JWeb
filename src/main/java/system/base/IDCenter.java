package system.base;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author wangchunzi
 */
final public class IDCenter {
    
    private static int i = 0;

    public final  static String getIID() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
                + new DecimalFormat("0000000").format(getI());
    }

    private static synchronized int getI() {
        i = i >= 9999999 ? 1 : ++i;
        return i;
    }
}
