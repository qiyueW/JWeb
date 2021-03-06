package system.web.filter.chain.config;

import java.lang.reflect.Array;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *
 * @author wangchunzi
 * @param <T>
 */
public class RegexData<T> {

    private final Set<RegexObject> set = new LinkedHashSet<>();

    public final int SORT_URL = 0;
    public final int SORT_METHOD = 1;

    public Set<Class> getResource(final String url, final String method) {
        Set<Class> sresult = new LinkedHashSet<>();
        for (RegexObject obj : set) {
            this.findResource(obj, sresult, url, method);
        }
        return sresult;
    }

    private void findResource(RegexObject<Class> ro, Set<Class> sresult, final String url, final String method) {
       
        switch (ro.sort) {
            case SORT_METHOD: {
                 System.out.println("执行绑定校验：" + ro.reget + "//" +"//method:" + method+"//校验结果"+Pattern.compile(ro.reget, Pattern.CASE_INSENSITIVE).matcher(method).matches());
                if (Pattern.compile(ro.reget, Pattern.CASE_INSENSITIVE).matcher(method).matches()) {
                    sresult.addAll(ro.lib);
                }
                return;
            }
            case SORT_URL: {
                System.out.println("执行绑定校验：" + ro.reget + "//url:" + url +"//校验结果"+Pattern.compile(ro.reget, Pattern.CASE_INSENSITIVE).matcher(url).matches());
                if (Pattern.compile(ro.reget, Pattern.CASE_INSENSITIVE).matcher(url).matches()) {
                    sresult.addAll(ro.lib);
                }
            }
        }
    }

    /**
     * 添加一个正规表达式-资源T 对象
     *
     * @param regex 表达式
     * @param t T资源
     * @param sort 资源类型
     */
    public void add(final String regex, T t, final int sort) {
        if (null == regex || regex.isEmpty()) {
            return;
        }

        T[] cs = (T[]) Array.newInstance(t.getClass(), 1);
        cs[0] = t;

        if (!regex.contains(",")) {
            this.set.add(new RegexObject(regex, cs, sort));
            return;
        }

        for (String str : regex.split(",")) {
            this.set.add(new RegexObject(str, cs, sort));
        }

    }

    /**
     * 添加一个正规表达式-资源T 对象
     *
     * @param regex 表达式
     * @param cs T资源组
     * @param sort 资源类型
     */
    public void add(String regex, T cs[], int sort) {
        if (null == regex || regex.isEmpty()) {
            return;
        }
        if (!regex.contains(",")) {
            this.set.add(new RegexObject(regex, cs, sort));
            return;
        }

        for (String str : regex.split(",")) {
            this.set.add(new RegexObject(str, cs, sort));
        }
    }

}
