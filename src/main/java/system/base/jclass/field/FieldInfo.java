package system.base.jclass.field;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import static system.base.jclass.ClassTypeCode.BOOLEAN;
import static system.base.jclass.ClassTypeCode.BYTE;
import static system.base.jclass.ClassTypeCode.DATE;
import static system.base.jclass.ClassTypeCode.DOUBLE;
import static system.base.jclass.ClassTypeCode.FLOAT;
import static system.base.jclass.ClassTypeCode.INTEGER;
import static system.base.jclass.ClassTypeCode.LONG;
import static system.base.jclass.ClassTypeCode.SHORT;

/**
 *
 * @author wangchunzi
 */
final public class FieldInfo {

    public final int instructions;
    public final Field field;
    public final Class fiel_type;
    public final String fiel_name;
    public final String date_format;
    public final String table_column_name;
    public final boolean unAuto;
    
    public FieldInfo( int instructions, Class fiel_type, String fiel_name, String date_format, String table_column_name, Field field,boolean aotuSQLField) {
        this.instructions = instructions;
        this.fiel_type = fiel_type;
        this.fiel_name = fiel_name;
        this.table_column_name = table_column_name;
        this.date_format = date_format;
        this.field = field;
        this.field.setAccessible(true);
        this.unAuto=aotuSQLField;
    }

    final public void setValueByDBResult(Object obj, java.sql.ResultSet rs) {
        try {
            this.field.set(obj, this.get(rs.getString(this.table_column_name)));
        } catch (java.sql.SQLException | IllegalArgumentException | IllegalAccessException ex) {
        }
    }

    final public void setValue(Object obj, Object obj2) {
        try {
            this.field.set(obj, obj2);
        } catch (IllegalArgumentException | IllegalAccessException ex) {

        }
    }
    
    final public boolean isNullField(Object obj, final boolean defaultValueByError) {
        try {
            return null == field.get(obj);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return defaultValueByError;
        }
    }

    /**
     * 将对象的字段值，取出，并转成'String'格式
     *
     * @param obj 实例
     * @return String
     */
    final public String getFormatValue(final Object obj) {
        try {
            if (null == this.field.get(obj)) {
                return null;
            }
            if (this.instructions == DATE) {
                return "'" + new java.text.SimpleDateFormat(this.date_format).format(this.field.get(obj)) + "'";
            }
            return "'" + this.field.get(obj).toString() + "'";
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(FieldInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * 将对象的字段值，取出，并转成"String"格式
     *
     * @param obj 实例
     * @param nullValue
     * @return String
     */
    final public String get(final Object obj, final String nullValue) {
        try {
            if (null == this.field.get(obj)) {
                return nullValue;
            }
            if (this.instructions == DATE) {
                return new java.text.SimpleDateFormat(this.date_format).format(this.field.get(obj));
            }
            return this.field.get(obj).toString();
        } catch (IllegalArgumentException | IllegalAccessException ex) {
        }
        return "";
    }

    final public Object get(final String str) {
        if (this.instructions == 0 || null == str) {
            return str;
        }
        switch (this.instructions) {
            case BYTE:
                return Byte.valueOf(str);
            case SHORT:
                return Short.valueOf(str);
            case INTEGER:
                return str.isEmpty()?0:Integer.valueOf(str);
            case LONG:
                return str.isEmpty()?0:Long.valueOf(str);
            case FLOAT:
                return str.isEmpty()?0:Float.valueOf(str);
            case DOUBLE:
                return str.isEmpty()?0:Double.valueOf(str);
            case BOOLEAN:
                Boolean.valueOf(str);
            case DATE:
                try {
                    return new java.text.SimpleDateFormat(this.date_format).parse(str);
                } catch (java.text.ParseException ex) {
                    ex.printStackTrace();
                }
                return null;
            default:
                return str;
        }
    }

    final public Object get(final String str, final String dateFormat, final String timeFormat) {
        if (this.instructions == 0 || null == str) {
            return str;
        }
        switch (this.instructions) {
            case BYTE:
                return Byte.valueOf(str);
            case SHORT:
                return str.isEmpty()?0:Short.valueOf(str);
            case INTEGER:
                return str.isEmpty()?0:Integer.valueOf(str);
            case LONG:
                return str.isEmpty()?0:Long.valueOf(str);
            case FLOAT:
                return str.isEmpty()?0:Float.valueOf(str);
            case DOUBLE:
                return str.isEmpty()?0:Double.valueOf(str);
            case BOOLEAN:
                Boolean.valueOf(str);
            case DATE:
                try {
                    return new java.text.SimpleDateFormat(
                            str.length() < timeFormat.length() ? dateFormat : timeFormat
                    ).parse(str);
                } catch (java.text.ParseException ex) {
                    System.out.println("时间转换出错:" + str + " 要求的格式:" + (str.length() < timeFormat.length() ? dateFormat : timeFormat));
                }
                return null;
            default:
                return str;
        }
    }
}
