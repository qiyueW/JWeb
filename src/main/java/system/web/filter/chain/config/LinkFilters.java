package system.web.filter.chain.config;

import java.util.Set;

/**
 *
 * @author wangchunzi
 */
public class LinkFilters {

    private final RegexData<Class> rd = new RegexData();

    public Set<Class> getResource(String url, String method) {
        return rd.getResource(url, method);
    }
    
    /**
     * 通过表达式regex，绑定N个过滤器c
     * <p>
     * 此方法用于@HM标注的申请路径
     *
     * @param regex -String 表达式
     * @param c -Class[] 过滤链集合数组
     * @return
     */
    public LinkFilters addFiltersByURL(String regex, Class[] c) {
        rd.add(regex, c, rd.SORT_URL);
        return this;
    }

    /**
     * 通过表达式regex，绑定N个过滤器c
     * <p>
     * 此方法用于@HM标注的申请路径
     *
     * @param regex -String 表达式
     * @param c -Class 过滤链集合数组
     * @return
     */
    public LinkFilters addFiltersByURL(String regex, Class c) {
        rd.add(regex, c, rd.SORT_URL);
        return this;
    }

    /**
     * 通过表达式regex，绑定N个过滤器c
     * <p>
     * 此方法用于(包.类.方法)的绑定
     *
     * @param regex -String 表达式
     * @param c -Class[] 过滤链集合
     * @return
     */
    public LinkFilters addFiltersByMethod(String regex, Class[] c) {
        rd.add(regex, c, rd.SORT_METHOD);
        return this;
    }

    /**
     * 通过表达式regex，绑定N个过滤器c
     * <p>
     * 此方法用于(包.类.方法)的绑定
     *
     * @param regex -String 表达式
     * @param c -Class 过滤链集合
     * @return
     */
    public LinkFilters addFiltersByMethod(String regex, Class c) {
        rd.add(regex, c, rd.SORT_METHOD);
        return this;
    }

}
