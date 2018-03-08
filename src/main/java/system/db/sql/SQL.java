package system.db.sql;

import java.util.List;
import static system.base.IDCenter.getIID;
import system.base.jclass.ClassFactory;
import system.base.jclass.ClassInfo;

/**
 *
 * @author wangchunzi
 */
final public class SQL implements Common, InsertSQL, DeleteSQL, UpdateSQL, SelectSQL {

    /**
     * 直接执行sql语句(增加、修改、删除)
     *
     * @param sql sql语句
     * @return 0|1
     */
    @Override
    final public int executeUpdate(final String sql) {
        return 0;
    }

    /**
     * InsertSQL
     *
     * @param obj 对象实例
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String addOne(final Object obj) {
        ClassInfo ci = ClassFactory.get(obj.getClass());
        StringBuilder sb = new StringBuilder();
        try {
            if (ci.unAuto) {
                sb.append("INSERT INTO ")
                        .append(ci.table_name).append("(").append(ci.table_column_name).append(") VALUES ")
                        .append(ci.getFieldString(obj,true));
            } else {
                sb.append("INSERT INTO ")
                        .append(ci.table_name).append("(").append(ci.table_column_name_add).append(") VALUES ")
                        .append(ci.getFieldString_Auto(obj,true));
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return null;
        }
        return sb.toString();
    }
    /**
     * 添加一篥记录
     * @param obj 对象实例
     * @return  sql格式化的字段语句
     */
     @Override
    final public String addOneByMyID(final Object obj) {
        ClassInfo ci = ClassFactory.get(obj.getClass());
        StringBuilder sb = new StringBuilder();
        try {
            if (ci.unAuto) {
                sb.append("INSERT INTO ")
                        .append(ci.table_name).append("(").append(ci.table_column_name).append(") VALUES ")
                        .append(ci.getFieldString(obj,false));
            } else {
                sb.append("INSERT INTO ")
                        .append(ci.table_name).append("(").append(ci.table_column_name_add).append(") VALUES ")
                        .append(ci.getFieldString_Auto(obj,false));
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return null;
        }
        return sb.toString();
    }

    /**
     * 解析添加对象，同时返回ID
     *
     * @param obj 操作实例
     * @return String[] [0]是sql,[1]是id
     */
    @Override
    public String[] addAndReturnID(Object obj) {
        ClassInfo ci = ClassFactory.get(obj.getClass());
        StringBuilder sb = new StringBuilder();
        String[] value;
        try {
            if (ci.unAuto) {
                value = ci.getFieldStringAndID(obj);
                sb.append("INSERT INTO ")
                        .append(ci.table_name).append("(").append(ci.table_column_name).append(") VALUES ")
                        .append(value[0]);
            } else {
                value = ci.getFieldStringAndID_Auto(obj);
                sb.append("INSERT INTO ")
                        .append(ci.table_name).append("(").append(ci.table_column_name_add).append(") VALUES ")
                        .append(value[0]);
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return null;
        }
        value[0] = sb.toString();
        return value;
    }

    /**
     * 生成批量操作语句
     *
     * @param <T> 泛型
     * @param objs 对象实例集合
     * @return 格式化的sql批处理语句 的字符串
     */
    @Override
    final public <T> String addVast(final List<T> objs) {
        ClassInfo ci = ClassFactory.get(objs.get(0).getClass());
        StringBuilder sb = new StringBuilder();

        String sql;
        try {
            if (ci.unAuto) {
                sql = "INSERT INTO " + ci.table_name + "(" + ci.table_column_name + ") VALUES ";
                for (T obj : objs) {
                    sb.append(",").append(ci.getFieldString(obj,true));
                }
            } else {
                sql = "INSERT INTO " + ci.table_name + "(" + ci.table_column_name_add + ") VALUES ";
                for (T obj : objs) {
                    sb.append(",").append(ci.getFieldString_Auto(obj,true));
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return null;
        }

        return sql + sb.substring(1);
    }

    /**
     * addOne_replace
     *
     * @param obj 类型实例
     * @param replaceName 执行要替换自定义值的字段
     * @param replaceValue 执行代换的值
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public <T> String addOne_replace(final T obj, final String replaceName, final String replaceValue) {
        ClassInfo ci = ClassFactory.get(obj.getClass());
        StringBuilder sb = new StringBuilder();
        try {
            if (ci.unAuto) {
                //1. insert into table(name...) values('id 
                sb.append("INSERT INTO ").append(ci.table_name)
                        .append("(").append(ci.table_column_name).append(") VALUES ('")
                        .append(getIID()).append("'");
                //2. 接上1处的id后 不断加入值,'v','v2','v3'...
                for (int i = 1; i < ci.fieldInfo.length; i++) {
                    sb.append(",").append(ci.fieldInfo[i].fiel_name.equals(replaceName) ? replaceValue : ci.fieldInfo[i].getFormatValue(obj));
                }
            } else {
                //1. insert into table(name...) values('id 
                sb.append("INSERT INTO ").append(ci.table_name)
                        .append("(").append(ci.table_column_name_add).append(") VALUES ('")
                        .append(getIID()).append("'");
                //2. 接上1处的id后 不断加入值,'v','v2','v3'...
                for (int i = 1; i < ci.fieldInfo.length; i++) {
                    if (ci.fieldInfo[i].unAuto) {//对非自动的，进行设置值
                        sb.append(",").append(ci.fieldInfo[i].fiel_name.equals(replaceName) ? replaceValue : ci.fieldInfo[i].getFormatValue(obj));
                    }
                }
            }
            //3. 接上2处。加入)。与1处最后的括号对应。 并返回
            return sb.append(")").toString();
        } catch (IllegalArgumentException ex) {
            return "";
        }
    }

    /**
     * 生成批量操作语句
     *
     * @param <T> 泛型未定类型
     * @param objs 对象集合
     * @param replaceName 替换字段的名字
     * @param replaceValue 替换的字段的值
     * @return 格式化的sql语句的字符串
     */
    @Override
    final public <T> String addVast_replace(final List<T> objs, final String replaceName, final String replaceValue) {
        ClassInfo ci = ClassFactory.get(objs.get(0).getClass());
        StringBuilder sql_body = new StringBuilder();

        try {
            if (ci.unAuto) {
                //1. insert into table(name...) values
                String sql_head = "INSERT INTO " + ci.table_name + "(" + ci.table_column_name + ") VALUES ";
                for (T obj : objs) {
                    //2. 接上1处的 VALUES 后 不断加入值:  ,('v','v2','v3'),('v','v2','v3'),('v','v2','v3'),('v','v2','v3')...
                    sql_body.append(",('").append(getIID()).append("'");//  idkey='idvalue'
                    for (int i = 1; i < ci.fieldInfo.length; i++) {
                        sql_body.append(",").append(ci.fieldInfo[i].fiel_name.equals(replaceName) ? replaceValue : ci.fieldInfo[i].getFormatValue(obj));
                    }
                    sql_body.append(")");
                }
                //3. 接上2处。加入)。与1处最后的括号对应。 并返回
                return sql_head + sql_body.substring(1);
            } else {
                //1. insert into table(name...) values
                String sql_head = "INSERT INTO " + ci.table_name + "(" + ci.table_column_name_add + ") VALUES ";
                for (T obj : objs) {
                    //2. 接上1处的 VALUES 后 不断加入值:  ,('v','v2','v3'),('v','v2','v3'),('v','v2','v3'),('v','v2','v3')...
                    sql_body.append(",('").append(getIID()).append("'");//  idkey='idvalue'
                    for (int i = 1; i < ci.fieldInfo.length; i++) {
                        if (ci.fieldInfo[i].unAuto) {
                            sql_body.append(",").append(ci.fieldInfo[i].fiel_name.equals(replaceName) ? replaceValue : ci.fieldInfo[i].getFormatValue(obj));
                        }
                    }
                    sql_body.append(")");
                }
                //3. 接上2处。加入)。与1处最后的括号对应。 并返回
                return sql_head + sql_body.substring(1);
            }
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    /**
     * 权限ID，删除一条记录
     *
     * @param c 操作类型
     * @param id 类关联的表的ID字段的值
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String dellByID(Class c, final String id) {
        ClassInfo ci = ClassFactory.get(c);
        return "DELETE FROM " + ci.table_name
                + " WHERE " + ci.fieldInfo[0].table_column_name + "='" + id + "'";
    }

    /**
     * 通过条件删除
     *
     * @param c 类型
     * @param condition 条件，必须抱含Where在头。
     * @return 格式化的SQL语句的字符串
     */
    @Override
    public String dellByCondition(Class c, final String condition) {
        ClassInfo ci = ClassFactory.get(c);
        return "DELETE FROM " + ci.table_name + " " + condition;
    }

    /**
     * 批量删除(ID形式:"id1,id2,id3............")
     *
     * @param c 类型
     * @param ids 多个id组成的字符串
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String dellVast(Class c, final String ids) {
        ClassInfo ci = ClassFactory.get(c);
        String id = "";
        for (String str : ids.split(",")) {
            id = id + ",'" + str + "'";
        }
        return "DELETE FROM " + ci.table_name
                + " WHERE " + ci.fieldInfo[0].table_column_name + " in(" + id.substring(1) + ") ";
    }

    /**
     * ids为 'v1','v2','v3','v4'..
     *
     * @param c 类型
     * @param ids 多个id组成的字符串
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String dellVast2(Class c, final String ids) {
        ClassInfo ci = ClassFactory.get(c);
        return "DELETE FROM " + ci.table_name
                + " WHERE " + ci.fieldInfo[0].table_column_name + " in(" + ids + ") ";
    }

    /**
     * 批量删除(ID形式：数组)
     *
     * @param c 类型
     * @param ids 多个id组成的字符串
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String dellVast(Class c, final String[] ids) {
        ClassInfo ci = ClassFactory.get(c);
        String id = "";
        for (String str : ids) {
            id = id + ",'" + str + "'";
        }
        return "DELETE FROM " + ci.table_name
                + " WHERE " + ci.fieldInfo[0].table_column_name + " in(" + id.substring(1) + ") ";
    }

    /**
     * 清空表
     *
     * @param c 类型
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String dellAll(Class c) {
        ClassInfo ci = ClassFactory.get(c);
        return "DELETE FROM " + ci.table_name;
    }

    /**
     * 更新对象。自动跳过为null的值
     *
     * @param obj 实例
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String update_notNull(Object obj) {
        ClassInfo ci = ClassFactory.get(obj.getClass());
        String setKV = "";
        try {
            String v;
            for (int i = 1; i < ci.fieldInfo.length; i++) {
                v = ci.fieldInfo[i].getFormatValue(obj);
                if (null != v) {
                    setKV = setKV + "," + ci.fieldInfo[i].table_column_name + "=" + v;
                }
            }
            return " UPDATE " + ci.table_name
                    + " SET " + setKV.substring(1)
                    + " WHERE " + ci.fieldInfo[0].table_column_name + "=" + ci.fieldInfo[0].getFormatValue(obj);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    /**
     * 更新正个对象各项属性(包括null)
     *
     * @param obj //更新实体
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String update_all(Object obj) {
        ClassInfo ci = ClassFactory.get(obj.getClass());
        String setKV = "";
        try {
            String v;
            for (int i = 1; i < ci.fieldInfo.length; i++) {
                v = ci.fieldInfo[i].getFormatValue(obj);
                setKV = setKV + "," + ci.fieldInfo[i].table_column_name + "=" + v;
            }
            return " UPDATE " + ci.table_name
                    + " SET " + setKV.substring(1)
                    + " WHERE " + ci.fieldInfo[0].table_column_name + "=" + ci.fieldInfo[0].getFormatValue(obj);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    /**
     * 根据ID更新（除开指定字段的值）。不管值是否为null
     *
     * @param obj //更新实体
     * @param rejectField 不更新的对象属性集体//“sdf,s2df,sdfdsfsd,sdfsdfdsf,sdfsdfsdfdf”
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String updateSome_reject(Object obj, final String rejectField) {

        ClassInfo ci = ClassFactory.get(obj.getClass());
        String setKV = "";
        try {
            String[] reject = rejectField.split(",");
            ccc:
            for (int i = 1; i < ci.fieldInfo.length; i++) {
                for (String r : reject) {
                    if (r.equals(ci.fieldInfo[i].fiel_name)) {
                        continue ccc;//在拒绝列表中，跳过下面代码，进行下一轮循环。
                    }
                }
                setKV = setKV + "," + ci.fieldInfo[i].table_column_name + "=" + ci.fieldInfo[i].getFormatValue(obj);
            }
            return " UPDATE " + ci.table_name
                    + " SET " + setKV.substring(1)
                    + " WHERE " + ci.fieldInfo[0].table_column_name + "=" + ci.fieldInfo[0].getFormatValue(obj);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    /**
     * 根据ID更新指定属性。不管值是否为null
     *
     * @param obj //更新实体
     * @param alloyField 更新的对象属性集体//“sdf,s2df,sdfdsfsd,sdfsdfdsf,sdfsdfsdfdf”
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String updateSome_alloy(Object obj, final String alloyField) {
        ClassInfo ci = ClassFactory.get(obj.getClass());
        String setKV = "";
        try {
            String[] alloy = alloyField.split(",");
            ccc:
            for (int i = 1; i < ci.fieldInfo.length; i++) {
                for (String r : alloy) {
                    if (r.equals(ci.fieldInfo[i].fiel_name)) {
                        setKV = setKV + "," + ci.fieldInfo[i].table_column_name + "=" + ci.fieldInfo[i].getFormatValue(obj);
                        continue ccc;//在拒绝列表中，跳过下面代码，进行下一轮循环。
                    }
                }
            }
            return " UPDATE " + ci.table_name
                    + " SET " + setKV.substring(1)
                    + " WHERE " + ci.fieldInfo[0].table_column_name + "=" + ci.fieldInfo[0].getFormatValue(obj);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    /**
     * 查询一条记录，根据ID
     *
     * @param c 类
     * @param id 类关联的表的id的值
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String selectOneByID(Class c, final String id) {
        ClassInfo ci = ClassFactory.get(c);
        return "SELECT * FROM " + ci.table_name
                + " WHERE " + ci.fieldInfo[0].table_column_name + "='" + id + "';";
    }

    /**
     * 查询一条记录。根据条件
     *
     * @param c 类
     * @param condition 条件——字符串
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String selectOneByCondition(Class c, final String condition) {
        ClassInfo ci = ClassFactory.get(c);
        return "SELECT * FROM " + ci.table_name + " " + condition + " LIMIT 1;";
    }

    /**
     * 查询表的总记录数
     *
     * @param c 类
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String selectCount(Class c) {
        return "SELECT COUNT(*) FROM " + ClassFactory.get(c).table_name;
    }

    /**
     * 查询表的记录数 根据条件
     *
     * @param c 类
     * @param condition 条件
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String selectCountByCondition(Class c, final String condition) {
        return "SELECT COUNT(*) FROM " + ClassFactory.get(c).table_name + " " + condition;
    }

    /**
     * 查询所有
     *
     * @param c 类
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String select(Class c) {
        return "SELECT * FROM " + ClassFactory.get(c).table_name;
    }

    /**
     * 查询所有，并进行自定义排序
     *
     * @param c 类
     * @param orderby 对于数据库字段前缀：无！
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String select(Class c, final String orderby) {
        return "SELECT * FROM " + ClassFactory.get(c).table_name + " " + orderby;
    }

    /**
     * 查询所有 根据条件
     *
     * @param c 类
     * @param condition 条件
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String selectByCondition(Class c, final String condition) {
        return "SELECT * FROM " + ClassFactory.get(c).table_name + " " + condition;
    }

    /**
     * 查询所有，根据条件，并进行自定义排序
     *
     * @param c 类
     * @param condition 条件
     * @param orderby 对于数据库字段前缀：无！
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String selectByCondition(Class c, final String condition, final String orderby) {
        ClassInfo ci = ClassFactory.get(c);
        return "SELECT * FROM " + ci.table_name + " " + condition + " " + orderby;
    }

    /**
     * 分页查询
     *
     * @param c 类
     * @param page 页码
     * @param pageCount 页的数据
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String selectVast(Class c, final int page, final int pageCount) {
        return "SELECT * FROM " + ClassFactory.get(c).table_name + " LIMIT " + (page - 1) * pageCount + "," + pageCount;
    }

    /**
     * 分页查询 并进行排序
     *
     * @param c 类
     * @param page 页码
     * @param pageCount 页的数据
     * @param orderby 排序
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String selectVast(Class c, int page, int pageCount, final String orderby) {
        return "SELECT * FROM " + ClassFactory.get(c).table_name
                + " " + orderby
                + " LIMIT " + (page - 1) * pageCount + "," + pageCount;
    }

    /**
     * 分页查询 根据条件
     *
     * @param c 类
     * @param page 页码
     * @param pageCount 页的数据
     * @param condition 条件
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String selectVastByCondition(Class c, final int page, final int pageCount, final String condition) {
        return "SELECT * FROM " + ClassFactory.get(c).table_name
                + " " + condition
                + " LIMIT " + (page - 1) * pageCount + "," + pageCount;
    }

    /**
     * 分页查询 根据条件 并进行排序
     *
     * @param c 类
     * @param page 页码
     * @param pageCount 页的数据
     * @param condition 条件
     * @param orderby 排序
     * @return 格式化的SQL语句的字符串
     */
    @Override
    final public String selectVastByCondition(Class c, final int page, final int pageCount, final String condition, final String orderby) {
        return "SELECT * FROM " + ClassFactory.get(c).table_name
                + " " + condition
                + " " + orderby
                + " LIMIT " + (page - 1) * pageCount + "," + pageCount;
    }

}
