package system.base.jclass;

import static system.base.IDCenter.getIID;
import system.base.jclass.field.FieldInfo;

/**
 *
 * @author wangchunzi
 */
final public class ClassInfo {

    public final String table_name;
    public final FieldInfo[] fieldInfo;
    public final boolean unAuto;

    /**
     * 例如: c1,c2,c3,c4....cn格式 第一个一定是ID
     */
    public final String table_column_name;
    public final String table_column_name_add;

    final public FieldInfo getFieldInfo(final String name) {
        for (FieldInfo fi : fieldInfo) {
            if (fi.fiel_name.equals(name)) {
                return fi;
            }
        }
        return null;
    }

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
     * @param obj
     * @param autoID
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    final public String getFieldString(final Object obj, final boolean autoID) throws IllegalArgumentException, IllegalAccessException {
        String value = autoID ? "'" + getIID() + "'" : fieldInfo[0].getFormatValue(obj);
        for (int i = 1; i < fieldInfo.length; i++) {
            value = value + "," + fieldInfo[i].getFormatValue(obj);
        }
        return "(" + value + ")";

    }

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
     * @param obj
     * @return String[] [0]是sql,[1]是id
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    final public String[] getFieldStringAndID(final Object obj) throws IllegalArgumentException, IllegalAccessException {
        String id = getIID();
        String value = "'" + id + "'";
        for (int i = 1; i < fieldInfo.length; i++) {
            value = value + "," + fieldInfo[i].getFormatValue(obj);
        }
        return new String[]{"(" + value + ")", id};
    }

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
