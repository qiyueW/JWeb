package system.base;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 为了广大群众的高要求，把999999改成9999999
 * <p>
 * 即，如果要产生重复ID,至少得每毫秒产生1000万个ID,同时，又碰上并发，才有可能引起重复。
 *
 * @author ik
 */
public class IID {

    private static int i = 0;

    public static String getIID() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date())
                + new DecimalFormat("0000000").format(getI());
    }

    synchronized private static int getI() {
        i = i >= 9999999 ? 1 : ++i;
        return i;
    }
}
