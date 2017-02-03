package system.web.filter.chain.config;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author wangchunzi
 * @param <T>
 */
public class RegexObject<T> {

    /**
     * 规则表达式
     */
    final public String reget;
    /**
     * 关联类型
     */
    final public Set<T> lib;

    final public int sort;

    public RegexObject(String reget, T[] libs, int sort) {
        this.reget = reget;
        Set<T> t = new LinkedHashSet();
        t.addAll(Arrays.asList(libs));
        this.lib = t;
        this.sort = sort;
    }

}
