package system.db.dao;

import java.util.List;
import system.base.jclass.ClassFactory;
import system.base.jclass.ClassInfo;
import system.db.dao.d.DeleteByCheckDao;
import system.db.dao.d.DeleteDao;
import system.db.dao.d.OODelete;
import system.db.dao.vo.CID;
import system.db.sql.SQL;

/**
 *
 * @author wangchunzi
 */
public class DeleteDaoImp implements DeleteDao, DeleteByCheckDao, OODelete {

    private final SQL sql;
    private final ADUS adus;

    public DeleteDaoImp(final ADUS adus, final SQL sql) {
        this.sql = sql;
        this.adus = adus;
    }

    /**
     * 根据ID，删除一条记录
     *
     * @param c
     * @param id
     * @return int
     */
    @Override
    public int dellByID(Class c, final String id) {
        return adus.executeUpdate(sql.dellByID(c, id));
    }

    /**
     * 通过条件删除
     *
     * @param c
     * @param condition
     * @return int
     */
    @Override
    public int dellByCondition(Class c, final String condition) {
        return adus.executeUpdate(sql.dellByCondition(c, condition));
    }

    /**
     * 批量删除(ID形式:"id1,id2,id3............")
     *
     * @param c
     * @param ids 表记录的主键集合 "id1,id2,id3,id4,i5...idN" 或为
     * "'id1','id2','id3','id4'.....'idN'"
     * @return int
     */
    @Override
    public int dellVast(Class c, final String ids) {
        return adus.executeUpdate(
                ids.contains("'")
                ? sql.dellVast2(c, ids)//采用  "'id1','id2','id3','id4'.....'idN'"
                : sql.dellVast(c, ids)//采用  "id1,id2,id3,id4,id5...idN"
        );
    }

    /**
     * 清空表
     *
     * @param c
     * @return int
     */
    @Override
    public int dellAll(Class c) {
        return adus.executeUpdate(sql.dellAll(c));
    }

    /**
     * 删除一条记录,删除前，先查check[]中所有的表是否有值根据id，有值则不执行删除，并返回-1;
     *
     * @param <T> 泛型
     * @param dellc 表关联的对象类(删除对象)
     * @param id 表记录的主键
     * @param denyCondition 本对象关联数据表的 阻止删除的条件(不要加入where!) 即会跟id 组装成 where
     * idname=id and denyCondition
     * @param check 查询类的集合
     * @return 0|1+|-1 0删除失败，1+删除成功，-1查询不通过
     */
    @Override
    public <T> int deleteOneByID_CheckToDeny(Class<T> dellc, String id, String denyCondition, Class... check) {
        ClassInfo ci = ClassFactory.get(dellc);
        if (!(null == denyCondition || denyCondition.isEmpty())) {//阻止删除的条件不为空。
            try {
                //查询要删除的记录，是否符合阻止条件
                T obj = adus.executeQueryOne(dellc, sql.selectOneByCondition(dellc, " where " + ci.fieldInfo[0].table_column_name + "='" + id + "' AND " + denyCondition));
                if (null != ci.fieldInfo[0].field.get(obj)) {
                    //不为空，符合阻止条件。返回-1
                    return -1;
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                return 0;
            }
        }
        String otherTableCheck = " WHERE " + ci.fieldInfo[0].table_column_name + "='" + id + "'";
        for (Class c : check) {
            if (adus.executeQueryCount(sql.selectCountByCondition(c, otherTableCheck)) > 0) {
                return -1;
            }
        }
        return adus.executeUpdate(sql.dellByID(dellc, id));
    }

    /**
     * 删除一条记录,删除前，先查check[]中所有的表是否有值根据id，有值则不执行删除，并返回-1;
     *
     * @param <T> 泛型
     * @param dellc 表关联的对象类(删除对象)
     * @param dellCondition 表记录的主键
     * @param denyCondition 本对象关联数据表的 阻止删除的条件(不要加入where!) 即会跟id 组装成 where
     * idname=id and denyCondition
     * @param check 查询类的集合
     * @return 0|1+|-1 0删除失败，1+删除成功，-1查询不通过
     */
    @Override
    public <T> int deleteOneByCondition_CheckToDeny(Class<T> dellc, String dellCondition, String denyCondition, Class... check) {
        try {
            ClassInfo ci = ClassFactory.get(dellc);
            T obj;
            if (!(null == denyCondition || denyCondition.isEmpty())) {//阻止删除的条件不为空。
                try {
                    //查询要删除的记录，是否符合阻止条件
                    obj = adus.executeQueryOne(dellc, sql.selectOneByCondition(dellc, dellCondition + " AND " + denyCondition));
                    if (null != ci.fieldInfo[0].field.get(obj)) {
                        //不为空，符合阻止条件。返回-1
                        return -1;
                    }
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    return 0;
                }
            }
            obj = adus.executeQueryOne(dellc, sql.selectOneByCondition(dellc, dellCondition));
            Object id = ci.fieldInfo[0].field.get(obj);
            if (null == id) {
                return 0;//没有可删除的记录。
            }
            String otherTableCheck = " WHERE " + ci.fieldInfo[0].table_column_name + "='" + id.toString() + "'";
            for (Class c : check) {
                if (adus.executeQueryCount(sql.selectCountByCondition(c, otherTableCheck)) > 0) {
                    return -1;
                }
            }
            return adus.executeUpdate(sql.dellByID(dellc, id.toString()));
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return 0;
        }
    }

    /**
     * 删除一条记录,删除前:
     * 
     * 1.先检查主表的阻止删除条件查询结束是否有值。如果有值，直接返回-1
     * 
     * 2.先查check[]中所有的表是否有值根据id，有值则不执行删除，并返回-1;
     * 
     *
     * @param <T> 泛型
     * @param dellc 表关联的对象类(删除对象)
     * @param ids 表记录的主键集合 "id1,id2,id3,id4,i5...idN" 或为 (不要加入where!)
     * "'id1','id2','id3','id4'.....'idN'"
     * @param denyCondition 本对象关联数据表的 阻止删除的条件(不要加入where!) 即会跟id 组装成 where
     * idname=id and denyCondition
     * @param check 查询类的集合
     * @return 0|1+|-1 0删除失败，1+删除成功，-1查询不通过
     */
    @Override
    public <T> int deleteVastByID_CheckToDeny(Class<T> dellc, String ids, String denyCondition, Class... check) {
        ClassInfo ci = ClassFactory.get(dellc);
        if (!ids.contains("'")) {
            String myid = "";
            for (String id : ids.split(",")) {
                myid = myid + ",'" + id + "'";
            }
            ids = myid.substring(1);
        }
        String idCondition = " WHERE " + ci.fieldInfo[0].table_column_name + " IN(" + ids + ")";
        //阻止删除的条件不为空。
        if (!(null == denyCondition || denyCondition.isEmpty())) {
//                //查询要删除的记录，是否符合阻止条件
//                T t = adus.executeQueryOne(dellc, sql.selectByCondition(dellc, idCondition + " AND " + denyCondition+" LIMIT 1"));
//                if (null != ci.fieldInfo[0].field.get(t)) {
//                    //不为空，符合阻止条件。返回-1
//                    return -1;
//                }
            if (adus.executeQueryCount(sql.selectCountByCondition(dellc, idCondition + " AND " + denyCondition + " LIMIT 1")) > 0) {
                return -1;
            }
        }
        for (Class c : check) {
            if (adus.executeQueryCount(sql.selectCountByCondition(c, idCondition + " LIMIT 1")) > 0) {
                return -1;
            }
        }
        return adus.executeUpdate(sql.dellByCondition(dellc, idCondition));
    }

    /**
     * 删除一条记录,删除前:
     * <p>
     * 1.先检查主表的阻止删除条件查询结束是否有值。如果有值，直接返回-1
     * <p>
     * 2.先查check[]中所有的表是否有值根据id，有值则不执行删除，并返回-1;
     * <p>
     *
     * @param <T> 泛型
     * @param dellc 表关联的对象类(删除对象)
     * @param dellCondition 表记录的主键集合 "id1,id2,id3,id4,i5...idN" 或为
     * "'id1','id2','id3','id4'.....'idN'"
     * @param denyCondition 本对象关联数据表的 阻止删除的条件(不要加入where!) 即会跟id 组装成 where
     * idname=id and denyCondition
     * @param check 查询类的集合
     * @return 0|1+|-1 0删除失败，1+删除成功，-1查询不通过
     */
    @Override
    public <T> int deleteVastByCondition_CheckToDeny(Class<T> dellc, String dellCondition, String denyCondition, Class... check) {
        ClassInfo ci = ClassFactory.get(dellc);
        List<T> listT;

        //1.阻止删除的条件不为空。
        if (!(null == denyCondition || denyCondition.isEmpty())) {
            try {
                //查询要删除的记录，是否符合阻止条件
                listT = adus.executeQueryVast(dellc, sql.selectByCondition(dellc, dellCondition + " AND " + denyCondition));
                for (T obj : listT) {
                    if (null != ci.fieldInfo[0].field.get(obj)) {
                        //不为空，符合阻止条件。返回-1
                        return -1;
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                return 0;
            }
        }
        //查询命中删除条件的id
        listT = adus.executeQueryVast(dellc, sql.selectByCondition(dellc, dellCondition));
        String myid = "";
        for (T t : listT) {
            try {
                if (null != ci.fieldInfo[0].field.get(t)) {
                    myid = myid + ",'" + ci.fieldInfo[0].field.get(t).toString() + "'";
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                return 0;
            }
        }
        if (myid.isEmpty()) {
            return 0;
        }
        String idCondition = " WHERE " + ci.fieldInfo[0].table_column_name + " IN(" + myid.substring(1) + ")";

        for (Class c : check) {
            if (adus.executeQueryCount(sql.selectCountByCondition(c, idCondition)) > 0) {
                return -1;
            }
        }
        return adus.executeUpdate(sql.dellByCondition(dellc, idCondition));
    }

    @Override
    public <T> int deleteVastByID_CheckToDeny_CID(Class<T> dellc, String ids, String denyCondition, CID... cid) {
        ClassInfo ci = ClassFactory.get(dellc);
        if (!ids.contains("'")) {
            String myid = "";
            for (String id : ids.split(",")) {
                myid = myid + ",'" + id + "'";
            }
            ids = myid.substring(1);
        }
        String idCondition = " WHERE " + ci.fieldInfo[0].table_column_name + " IN(" + ids + ")";
        //阻止删除的条件不为空。
        if (!(null == denyCondition || denyCondition.isEmpty())) {
            try {
                //查询要删除的记录，是否符合阻止条件
                List<T> listT = adus.executeQueryVast(dellc, sql.selectByCondition(dellc, idCondition + " AND " + denyCondition));
                for (T obj : listT) {
                    if (null != ci.fieldInfo[0].field.get(obj)) {
                        //不为空，符合阻止条件。返回-1
                        return -1;
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                return 0;
            }
        }

        for (CID ciobj : cid) {
            if (adus.executeQueryCount(sql.selectCountByCondition(ciobj.c, null == ciobj.id ? idCondition : " WHERE " + ciobj.id + " IN(" + ids + ")")) > 0) {
                return -1;
            }
        }
        return adus.executeUpdate(sql.dellByCondition(dellc, idCondition));
    }

    @Override
    public int ooDelete(String ids, Class... needDelete) {
        ClassInfo ci = ClassFactory.get(needDelete[0]);//主表
        if (!ids.contains("'")) {
            String myid = "";
            for (String id : ids.split(",")) {
                myid = myid + ",'" + id + "'";
            }
            ids = myid.substring(1);
        }
        String[] msql = new String[needDelete.length];
        msql[0] = sql.dellVast2(needDelete[0], ids);//主的
        String idCondition = " WHERE " + ci.fieldInfo[0].table_column_name + " IN(" + ids + ")";

        for (int i = 1; i < needDelete.length; i++) {
            msql[i] = sql.dellByCondition(needDelete[i], idCondition);
        }
        int[] irs = adus.executeBatch(msql);
        return null == irs ? -1 : irs[0];
    }

}
