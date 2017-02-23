package system.db.dao;

import java.util.List;
import system.base.jclass.ClassFactory;
import system.base.jclass.ClassInfo;
import system.base.jclass.field.FieldInfo;
import system.db.dao.i.AddDao;
import system.db.dao.i.Add_OO_OM_Dao;
import system.db.sql.SQL;

/**
 *
 * @author wangchunzi
 */
public class AddDaoImp implements AddDao, Add_OO_OM_Dao {

    private final SQL sql;
    private final ADUS adus;

    public AddDaoImp(final ADUS adus, final SQL sql) {
        this.sql = sql;
        this.adus = adus;
    }

    /**
     * 添加。
     *
     * @param obj
     * @param unique
     * @return int
     */
    @Override
    public int addOne(Object obj, String... unique) {

        if (unique.length == 0) {
            return adus.executeUpdate(sql.addOne(obj));
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
                        // adus.executeQueryOne(c, sql.selectOneByCondition(c, condition));
                        if (!ci.fieldInfo[0].isNullField(
                                adus.executeQueryOne(
                                        obj.getClass(), sql.selectOneByCondition(obj.getClass(), "WHERE " + sb.substring(4))), false)) {
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
     * 批量操作语句
     *
     * @param <T>
     * @param list
     * @return int
     */
    @Override
    public <T> int addVast(List<T> list, String... unique) {
        if (unique.length == 0) {
            return adus.executeUpdate(sql.addVast(list));
        }
        Class tc = list.get(0).getClass();
        ClassInfo ci = ClassFactory.get(tc);
        FieldInfo[] mfi = new FieldInfo[unique.length];
        int j = 0;
        for (FieldInfo f : ci.fieldInfo) {
            for (String u : unique) {
                if (f.fiel_name.equals(u)) {
                    mfi[j++] = f;
                }
            }
        }
        String in;
        for (FieldInfo myfi : mfi) {
            in = "";
            for (T obj : list) {
                in = in + "," + myfi.getFormatValue(obj);
            }
            if (!myfi.isNullField(
                    adus.executeQueryOne(
                            tc, sql.selectByCondition(tc, "WHERE " + myfi.table_column_name + " IN(" + in.substring(1) + ")")
                    ), false)) {
                return -1;
            }
        }
        return adus.executeUpdate(sql.addVast(list));
    }

    @Override
    public <O, O2> int[] add_OO(O o, O2... o2) {
        int length=o2.length+1;
        ClassInfo ci = ClassFactory.get(o.getClass());
        String[] osql = sql.addAndReturnID(o2);
        String[] o2sql = new String[length];
        o2sql[0]=osql[0];
        
        for (int i = 1; i < length; i++) {
            o2sql[i]=sql.addOne_replace(o2[i-1], ci.fieldInfo[0].fiel_name, osql[1]);
        }
        return adus.executeBatch(o2sql);
    }

    @Override
    public <O, M> int[] add_OM(O o, List<M>... m) {
        int length=m.length+1;
        ClassInfo ci = ClassFactory.get(m[0].get(0).getClass());
        
        String[] osql = sql.addAndReturnID(o);
        String[] o2sql = new String[length];
        o2sql[0]=osql[0];
        for (int i = 1; i < length; i++) {
            o2sql[i]=sql.addVast_replace(m[i-1], ci.fieldInfo[0].fiel_name, osql[1]);
        }
        return adus.executeBatch(o2sql);
    }

}
