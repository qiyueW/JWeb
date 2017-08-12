package system.web.power.data;

import java.util.Comparator;

/**
 *
 * @author jweb
 */
public class PowerComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        IPowerData p1 = (IPowerData) o1;
        IPowerData p2 = (IPowerData) o2;
        return p1.key() - p2.key();
    }
}
