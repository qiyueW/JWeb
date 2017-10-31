package system.base;

import system.base.str.WhereStr;

/**
 *
 * @author fly
 */
public class StringTool {

    public final static WhereStr OpenWhereStr() {
        return new WhereStr();
    }

    /**
     * 将字段串a,b,c 变成 字段串 'a','b','c'
     *
     * @param s 默认值为null或空，默认返回''
     * @return
     */
    public static final String replaceDToDDD(String s) {
        return null == s || s.isEmpty() ? "''" : "'" + s.replace(",", "','") + "'";
    }
}
