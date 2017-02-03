package system.web.filter.chain;

import system.web.JWeb;

/**
 *
 * @author wangchunzi
 */
public abstract class FilterModel {

    private int location = 0;
    /**
     * 顶部(在校验之前)
     */
    public static final int LOCATION_TOP = 0;

    /**
     * 中部(在校难后，sevlet前)
     */
    public static final int LOCATION_CENTER = 1;

    /**
     * 尾部(在servlet后)
     */
    public static final int LOCATION_BUTTOM = 2;

    /**
     *
     * @param jw // * @param filterConfig
     */
    public abstract void doFilter(JWeb jw);

    /**
     * 设置前置|后置
     *
     * @param i
     */
    public void setLocation(int i) {
        this.location = 0 > i || i > 2 ? 0 : i;
    }

    public int getLocation() {
        return this.location;
    }
}
