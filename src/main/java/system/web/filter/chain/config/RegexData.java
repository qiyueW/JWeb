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

    private String toRegex(String str) {
        StringBuilder sb = new StringBuilder();
        final char code_x = '*';
        String code_x_All = "[a-zA-Z0-9/.]+";
        String code_x_One = "[a-zA-Z0-9]+";
        char strs[] = str.toCharArray();
        int indexlen = strs.length - 1;

        for (int i = 0; i < strs.length; i++) {
            sb.append(
                    strs[i] == code_x
                    ? (i == indexlen ? code_x_All : code_x_One)
                    : strs[i] + "{1}"
            );
        }
//        System.out.println(sb);
        return sb.toString();
    }

    public Set<Class> getResource(final String url, final String method) {
        Set<Class> sresult = new LinkedHashSet<>();
        for (RegexObject obj : set) {
            this.findResource(obj, sresult, url, method);
        }
        return sresult;
    }

    private void findResource(RegexObject<Class> ro, Set<Class> sresult, final String url, final String method) {
//        System.out.println("执行绑定校验：" + ro.reget + "//" + url + "//" + method);
        switch (ro.sort) {
            case SORT_METHOD: {
                if (Pattern.compile(ro.reget, Pattern.CASE_INSENSITIVE).matcher(method).matches()) {
                    sresult.addAll(ro.lib);
                }
                return;
            }
            case SORT_URL: {
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
    public void add(String regex, T t, int sort) {
        if (null == regex || regex.isEmpty()) {
            return;
        }

        T[] cs = (T[]) Array.newInstance(t.getClass(), 1);
        cs[0] = t;

        if (!regex.contains(",")) {
            this.set.add(new RegexObject(this.toRegex(regex), cs, sort));
            return;
        }

        for (String str : regex.split(",")) {
            this.set.add(new RegexObject(this.toRegex(str), cs, sort));
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
            this.set.add(new RegexObject(this.toRegex(regex), cs, sort));
            return;
        }

        for (String str : regex.split(",")) {
            this.set.add(new RegexObject(this.toRegex(str), cs, sort));
        }
    }

}
