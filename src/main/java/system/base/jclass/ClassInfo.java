package system.base.jclass;

import static system.base.IDCenter.getIID;
import system.base.jclass.field.FieldInfo;

/**
 *
 * @author wangchunzi
 */
final public class ClassInfo {

    /**
     * 数据表名
     */
    public final String table_name;
    /**
     * 属性相关数据的集合
     */
    public final FieldInfo[] fieldInfo;

    /**
     * 标注是否是自动值
     */
    public final boolean unAuto;

    /**
     * 例如: c1,c2,c3,c4....cn格式 第一个一定是ID
     */
    public final String table_column_name;
    /**
     * 用于批添加的形态
     */
    public final String table_column_name_add;

    /**
     * 得到属性相关数据
     *
     * @param name String 字段名
     * @return FieldInfo
     */
    final public FieldInfo getFieldInfo(final String name) {
        for (FieldInfo fi : fieldInfo) {
            if (fi.fiel_name.equals(name)) {
                return fi;
            }
        }
        return null;
    }

    /**
     * 构造方法
     *
     * @param table_name 关联数据库表名
     * @param fieldInfo 属性的相关数据
     * @param aotuSQLField 是否自动类型的字段
     */
    public ClassInfo(String table_name, FieldInfo[] fieldInfo, boolean aotuSQLField) {
        this.table_name = table_name;
        this.fieldInfo = fieldInfo;
        String names = "";
        String namesAdd = "";
        for (FieldInfo f : fieldInfo) {
            names = names + "," + f.table_column_name;
            if (f.unAuto) {
                namesAdd = namesAdd + "," + f.table_column_name;
            }
        }

        this.table_column_name = names.substring(1);
        this.table_column_name_add = namesAdd.length() > 0 ? namesAdd.substring(1) : "";
        this.unAuto = aotuSQLField;
    }

    /**
     * 将对象实例的fieldInfo属性值，转成"'v','v2','v3'....'vn'"这样的字符串，值的顺序与本实例的属性table_column_name的字段名一致
     *
     * @param obj Object
     * @param autoID boolean
     * @return String
     * @throws IllegalArgumentException 异常
     * @throws IllegalAccessException 异常
     */
    final public String getFieldString(final Object obj, final boolean autoID) throws IllegalArgumentException, IllegalAccessException {
        String value = autoID ? "'" + getIID() + "'" : fieldInfo[0].getFormatValue(obj);
        for (int i = 1; i < fieldInfo.length; i++) {
            value = value + "," + fieldInfo[i].getFormatValue(obj);
        }
        return "(" + value + ")";

    }

    /**
     * 将对象实例的fieldInfo属性值，转成"'v','v2','v3'....'vn'"这样的字符串，值的顺序与本实例的属性table_column_name的字段名一致
     *
     * @param obj Object
     * @param autoID boolean
     * @return String
     * @throws IllegalArgumentException 异常
     * @throws IllegalAccessException 异常
     */
    final public String getFieldString_Auto(final Object obj, final boolean autoID) throws IllegalArgumentException, IllegalAccessException {
        String value = autoID ? "'" + getIID() + "'" : fieldInfo[0].getFormatValue(obj);
        for (int i = 1; i < fieldInfo.length; i++) {
            if (fieldInfo[i].unAuto) {
                value = value + "," + fieldInfo[i].getFormatValue(obj);
            }
        }
        return "(" + value + ")";
    }

    /**
     * 将对象实例的fieldInfo属性值，转成"'v','v2','v3'....'vn'"这样的字符串，值的顺序与本实例的属性table_column_name的字段名一致
     *
     * @param obj Object
     * @return String[] [0]是sql,[1]是id
     * @throws IllegalArgumentException 异常
     * @throws IllegalAccessException 异常
     */
    final public String[] getFieldStringAndID(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        String id = getIID();
        String value = "'" + id + "'";
        for (int i = 1; i < fieldInfo.length; i++) {
            value = value + "," + fieldInfo[i].getFormatValue(obj);
        }
        return new String[]{"(" + value + ")", id};
    }

    /**
     * 取得字段名字的拼接成的字段串 [0]，[1]表示ID
     *
     * @param obj Object 对象实例
     * @return String[] [0]:除ID外的所有字段的拼接成的字符串，[1]表示ID
     * @throws IllegalArgumentException 异常
     * @throws IllegalAccessException 异常
     */
    final public String[] getFieldStringAndID_Auto(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        String id = getIID();
        String value = "'" + id + "'";
        for (int i = 1; i < fieldInfo.length; i++) {
            if (fieldInfo[i].unAuto) {
                value = value + "," + fieldInfo[i].getFormatValue(obj);
            }
        }
        return new String[]{"(" + value + ")", id};
    }
}
