package system.base.str;

/**
 *
 * @author fly
 */
public class WhereStr {

    /**
     * 代表数据
     */
    public enum valueX {
        notNull(0), notEmpty(1), notNullAndNotEmpty(2),
        alloyNull(10), alloyEmpty(11), alloyNullAndEmpty(12);
        public final int key;

        private valueX(int i) {
            key = i;
        }
    }
    /**
     * 字符容器
     */
    final private StringBuilder sb = new StringBuilder();

    /**
     * 取出拼成的sql语句
     *
     * @return String
     */
    public String getSQL() {
        return sb.toString();
    }

    /**
     * 取出拼成的sql语句 （非空情况下，会带有WHERE前缀）
     *
     * @return String
     */
    public String geteWhereSQL() {
        return sb.length() > 0 ? "WHERE " + sb.toString() : "";
    }

    /**
     * 加入自定义条件 。
     *
     * @param tj 条件
     * @return WhereStr
     */
    public WhereStr putAND(String tj) {
        if (null == tj || tj.isEmpty()) {
            return this;
        }
        sb.append(sb.length() == 0 ? tj : " AND " + tj);
        return this;
    }

    /**
     * 加入或条件
     *
     * @param tj 条件
     * @return WhereStr
     */
    public WhereStr putOR(String tj) {
        if (null == tj || tj.isEmpty()) {
            return this;
        }
        sb.append(sb.length() == 0 ? tj : " OR " + tj);
        return this;
    }

    /**
     * 检查值是否合法
     *
     * @param dov 检查方案
     * @param v 值
     * @return boolean结果
     */
    private static boolean valueIsError(valueX dov, String v) {
        switch (dov.key) {
            case 0: {  //notNull(0) 非null
                return null == v;//null成立，返回true
            }
            case 1: {//notEmpty(1) 非空
                return null != v && v.trim().isEmpty();//在非null状态时，空成立,返回true。
            }
            case 2: {//notNullAndNotEmpty(2) 非null非空
                return null == v || v.trim().isEmpty();//null或空成立，返回true
            }
            case 10: {//alloyNull(10) 允许null，不允许非空
                return null != v && v.trim().isEmpty();//在非null状态时，空成立,返回true。
            }
            case 11: {// alloyEmpty(11) 允许空，不允许null
                return null == v;//null成立，返回true
            }
            case 12: {// alloyNullAndEmpty(12); 允许空，允许null
                return false;
            }
            default:
                return true;
        }
    }

    /**
     * 放入名与值的关系
     * <p>
     * 值默认用''包着 在非null情况下
     *
     * @param dov 值允许的类型
     * @param ljf 连接前面的符号
     * @param n 名
     * @param bjf 比较符（等于，大于，小于，不等于）
     * @param v 值
     * @return WhereStr
     */
    public WhereStr putBjf(valueX dov, String ljf, String n, String bjf, String v) {
        if (valueIsError(dov, v)) {
            return this;
        }
        if (null != v) {
            v = "'" + v + "'";
        }
        if (sb.length() == 0) {
            sb.append(" ").append(n).append(bjf).append(v);
            return this;
        }
        sb.append(" ").append(ljf).append(" ").append(n).append(bjf).append(v);
        return this;
    }

    /**
     * 放入名与值的关系 IN逻辑关系
     *
     * @param dov 值允许的类型
     * @param ljf 连接前面的符号
     * @param n 名
     * @param v 值
     * @return WhereStr
     */
    public WhereStr putLjf_IN(valueX dov, String ljf, String n, String v) {
        if (valueIsError(dov, v)) {
            return this;
        }
        if (sb.length() == 0) {
            sb.append(" ").append(n).append(" IN(").append(v).append(")");
            return this;
        }
        sb.append(" ").append(ljf).append(" ").append(n).append(" IN(").append(v).append(")");
        return this;
    }

    /**
     * 放入名与值的关系 LIKE逻辑关系
     * <p>
     * 值默认用''包着
     *
     * @param dov 值允许的类型
     * @param ljf 连接前面的符号
     * @param n 名
     * @param v 值
     * @return WhereStr
     */
    public WhereStr putLjf_LIKE(valueX dov, String ljf, String n, String v) {
        if (valueIsError(dov, v)) {
            return this;
        }
        if (sb.length() == 0) {
            sb.append(" ").append(n).append(" LIKE '").append(v).append("'");
            return this;
        }
        sb.append(" ").append(ljf).append(" ").append(n).append(" LIKE '").append(v).append("'");
        return this;
    }

    /**
     * 清空数据
     */
    public void resetObject() {
        sb.setLength(0);
    }
}
