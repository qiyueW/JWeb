package system.db.dao;

import system.base.jclass.ClassFactory;
import system.base.jclass.ClassInfo;
import system.db.dao.u.UpdateDao;
import system.db.sql.SQL;

/**
 *
 * @author wangchunzi
 */
public class UpdateDaoImp implements UpdateDao {

    private final SQL sql;
    private final ADUS adus;

    public UpdateDaoImp(final ADUS adus, final SQL sql) {
        this.sql = sql;
        this.adus = adus;
    }

    /**
     * 更新对象。自动跳过为null的值
     *
     * @param obj
     * @return boolean
     */
    @Override
    public int update_notNull(Object obj, String... unique) {
        if (unique.length == 0) {
            return adus.executeUpdate(sql.update_notNull(obj));
        }
        ClassInfo ci = ClassFactory.get(obj.getClass());
        StringBuilder sb = new StringBuilder();
        int j = 0;

        for (int i = 1; i < ci.fieldInfo.length; i++) {
            for (String u : unique) {
                if (ci.fieldInfo[i].fiel_name.equals(u)) {//检查是否我们要找到字段
                    j++;//用于计数 unique中的元素是否找齐了。找齐了即时进入数据库查询。
                    //是我们要找的字段。则，采用 xx='值' OR xx='值'...... 方式进行查询数据库。
                    sb.append(" OR ").append(ci.fieldInfo[i].table_column_name).append("=").append(ci.fieldInfo[i].getFormatValue(obj));
                    //检查我们的唯一列的集合是否找齐。
                    if (j == unique.length) {
                        //当前唯一列是最后一个。我们找齐了。执行数据库查询。如果唯一列集合中，任意一列有值，直接返回-1
                        if (!ci.fieldInfo[0].isNullField(
                                adus.executeQueryOne(
                                        obj.getClass(), sql.selectOneByCondition(
                                        obj.getClass(), "WHERE " + ci.fieldInfo[0].table_column_name + "<>" + ci.fieldInfo[0].getFormatValue(obj) + " AND (" + sb.substring(4) + ")")
                                ), false)) {
                            return -1;
                        }
                        return adus.executeUpdate(sql.addOne(obj));
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 更新对象obj(除id外，所有的属性值)
     *
     * @param obj
     * @return boolean
     */
    @Override
    public int update_all(Object obj, String... unique) {
        if (unique.length == 0) {
            return adus.executeUpdate(sql.update_all(obj));
        }
        ClassInfo ci = ClassFactory.get(obj.getClass());
        StringBuilder sb = new StringBuilder();
        int j = 0;

        for (int i = 1; i < ci.fieldInfo.length; i++) {
            for (String u : unique) {
                if (ci.fieldInfo[i].fiel_name.equals(u)) {//检查是否我们要找到字段
                    j++;//用于计数 unique中的元素是否找齐了。找齐了即时进入数据库查询。
                    //是我们要找的字段。则，采用 xx='值' OR xx='值'...... 方式进行查询数据库。
                    sb.append(" OR ").append(ci.fieldInfo[i].table_column_name).append("=").append(ci.fieldInfo[i].getFormatValue(obj));
                    //检查我们的唯一列的集合是否找齐。
                    if (j == unique.length) {
                        //当前唯一列是最后一个。我们找齐了。执行数据库查询。如果唯一列集合中，任意一列有值，直接返回-1
                        if (!ci.fieldInfo[0].isNullField(
                                adus.executeQueryOne(
                                        obj.getClass(), sql.selectOneByCondition(
                                        obj.getClass(), "WHERE " + ci.fieldInfo[0].table_column_name + "<>" + ci.fieldInfo[0].getFormatValue(obj) + " AND (" + sb.substring(4) + ")")
                                ), false)) {
                            return -1;
                        }
                        return adus.executeUpdate(sql.update_all(obj));
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 根据ID更新（除开指定字段的值）。不管值是否为null
     *
     * @param obj //更新实体
     * @param rejectField 不更新的对象属性集体//“sdf,s2df,sdfdsfsd,sdfsdfdsf,sdfsdfsdfdf”
     * @return
     */
    @Override
    public int updateSome_reject(Object obj, String rejectField, String... unique) {
        if (unique.length == 0) {
            return adus.executeUpdate(sql.updateSome_reject(obj, rejectField));
        }
        ClassInfo ci = ClassFactory.get(obj.getClass());
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for (int i = 1; i < ci.fieldInfo.length; i++) {
            for (String u : unique) {
                if (ci.fieldInfo[i].fiel_name.equals(u)) {//检查是否我们要找到字段
                    j++;//用于计数 unique中的元素是否找齐了。找齐了即时进入数据库查询。
                    //是我们要找的字段。则，采用 xx='值' OR xx='值'...... 方式进行查询数据库。
                    sb.append(" OR ").append(ci.fieldInfo[i].table_column_name).append("=").append(ci.fieldInfo[i].getFormatValue(obj));
                    //检查我们的唯一列的集合是否找齐。
                    if (j == unique.length) {
                        //当前唯一列是最后一个。我们找齐了。执行数据库查询。如果唯一列集合中，任意一列有值，直接返回-1
                        if (!ci.fieldInfo[0].isNullField(
                                adus.executeQueryOne(
                                        obj.getClass(), sql.selectOneByCondition(
                                        obj.getClass(), "WHERE " + ci.fieldInfo[0].table_column_name + "<>" + ci.fieldInfo[0].getFormatValue(obj) + " AND (" + sb.substring(4) + ")")
                                ), false)) {
                            return -1;
                        }
                        return adus.executeUpdate(sql.updateSome_reject(obj, rejectField));
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public int updateSome_alloy(Object obj, String alloyField, String... unique) {
        if (unique.length == 0) {
            return adus.executeUpdate(sql.updateSome_alloy(obj, alloyField));
        }
        ClassInfo ci = ClassFactory.get(obj.getClass());
        StringBuilder sb = new StringBuilder();
        int j = 0;

        for (int i = 1; i < ci.fieldInfo.length; i++) {
            for (String u : unique) {
                if (ci.fieldInfo[i].fiel_name.equals(u)) {//检查是否我们要找到字段
                    j++;//用于计数 unique中的元素是否找齐了。找齐了即时进入数据库查询。
                    //是我们要找的字段。则，采用 xx='值' OR xx='值'...... 方式进行查询数据库。
                    sb.append(" OR ").append(ci.fieldInfo[i].table_column_name).append("=").append(ci.fieldInfo[i].getFormatValue(obj));
                    //检查我们的唯一列的集合是否找齐。
                    if (j == unique.length) {
                        //当前唯一列是最后一个。我们找齐了。执行数据库查询。如果唯一列集合中，任意一列有值，直接返回-1
                        if (!ci.fieldInfo[0].isNullField(
                                adus.executeQueryOne(
                                        obj.getClass(), sql.selectOneByCondition(
                                        obj.getClass(), "WHERE " + ci.fieldInfo[0].table_column_name + "<>" + ci.fieldInfo[0].getFormatValue(obj) + " AND (" + sb.substring(4) + ")")
                                ), false)) {
                            return -1;
                        }
                        return adus.executeUpdate(sql.updateSome_reject(obj, alloyField));
                    }
                }
            }
        }
        return 0;
    }

}
