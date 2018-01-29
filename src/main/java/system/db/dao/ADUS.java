package system.db.dao;

import system.db.dao.adus.ADUSDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import system.base.exception.DOEFactory;
import system.base.jclass.ClassFactory;
import system.base.jclass.ClassInfo;
import system.base.jclass.field.FieldInfo;
import system.db.pool.ADUPool;
import system.db.pool.ADUSession;
import system.db.pool.SPool;
import system.db.pool.SSession;

/**
 *
 * @author wangchunzi
 */
final public class ADUS implements ADUSDao {

    private final ADUPool adupool;
    private final SPool spool;

    public ADUS(final ADUPool adupool, final SPool spool) {
        this.adupool = adupool;
        this.spool = spool;
    }

    /**
     * 执行查询 一条记录
     *
     * @param <T>
     * @param c
     * @param sql
     * @return T
     */
    @Override
    final public <T> T executeQueryOne(Class<T> c, final String sql) {
//        System.out.println("In the queryOne:" + sql);
        ClassInfo ci = ClassFactory.get(c);
        T obj;
        try {
            obj = c.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            DOEFactory.getDOE().newInstance(sql);
            return null;
        }
        Statement statement = null;
        ResultSet select;
        SSession session = spool.getSSession();
        try {
            statement = session.getStatement();
            select = statement.executeQuery(sql);
            if (select.next()) {
                for (FieldInfo fi : ci.fieldInfo) {
                    fi.setValueByDBResult(obj, select);
                }
            }
            select.close();
        } catch (SQLException ex) {
            DOEFactory.getDOE().executeQuery(sql);
            return null;
        } finally {
            if (null != statement) {
                session.close(statement);
            }
        }
        return obj;
    }

    /**
     * 执行查询 多条记录
     *
     * @param <T>
     * @param c
     * @param sql
     * @return List
     */
    @Override
    final public <T> List<T> executeQueryVast(Class<T> c, final String sql) {
//        System.out.println(sql);
        ClassInfo ci = ClassFactory.get(c);
        T obj;
        List<T> list = new ArrayList<>();
        Statement statement = null;
        ResultSet select;
        SSession session = spool.getSSession();//从池中得到一个连接
        try {
            statement = session.getStatement();//生成一个操作数据库实例
            select = statement.executeQuery(sql);//执行sql发送及 接收(返回)
            while (select.next()) {//如果有值
                obj = c.newInstance();//执行实例一个装箱对象
                for (FieldInfo fi : ci.fieldInfo) {
                    fi.setValueByDBResult(obj, select);//执行装箱
                }
                list.add(obj);//放入集合
            }
            select.close();//关闭结果集通道
            return list;
        } catch (SQLException ex) {
            DOEFactory.getDOE().executeQuery(sql);
            return null;
        } catch (InstantiationException | IllegalAccessException ex) {
            DOEFactory.getDOE().newInstance(sql);
        } finally {
            if (null != statement) {
                session.close(statement);
            }
        }
        return list;
    }

    /**
     * 统计
     *
     * @param sql
     * @return int
     */
    @Override
    final public int executeQueryCount(final String sql) {
//        System.out.println(sql);
        Statement statement = null;
        ResultSet select;
        SSession session = spool.getSSession();
        try {
            statement = session.getStatement();
            select = statement.executeQuery(sql);
            select.next();
            int i = select.getInt(1);
            select.close();
            return i;
        } catch (SQLException ex) {
            DOEFactory.getDOE().executeQuery(sql);
            return -1;
        } finally {
            if (null != statement) {
                session.close(statement);
            }
        }
    }

    @Override
    final public int executeUpdate(String sql) {
//        System.out.println(sql);
        ADUSession as = this.adupool.getIndexConnection();
        try {
            Statement cs = as.getConn().createStatement();
            int i = cs.executeUpdate(sql);
            as.getConn().commit();
            cs.close();
            return i;
        } catch (SQLException ex) {
            DOEFactory.getDOE().executeUpdate(sql);
            try {
                as.getConn().rollback();
                DOEFactory.getDOE().executeUpdate_rollback_success(sql);
            } catch (SQLException ex1) {
                DOEFactory.getDOE().executeUpdate_rollback_error(sql);
            }
            return -1;
        } finally {
            as.close();
        }
    }

    @Override
    final public int[] executeBatch(String... SQL) {
//        System.out.println("=========事务管理-展示SQL 上==========\n");
//        for (String temp : SQL) {
//            System.out.println(temp + "\n");
//        }
//        System.out.println("=========事务管理-展示SQL 下==========\n");

        ADUSession as = this.adupool.getIndexConnection();
        try {
            Statement cs = as.getConn().createStatement();
            for (String mysql : SQL) {
                cs.addBatch(mysql);
            }
            int[] a = cs.executeBatch();
            as.getConn().commit();
            cs.close();
            return a;
        } catch (SQLException ex) {
            DOEFactory.getDOE().executeBatch(SQL);
            try {
                as.getConn().rollback();
               DOEFactory.getDOE().executeBatch_rollback_success(SQL);
            } catch (SQLException ex1) {
               DOEFactory.getDOE().executeBatch_rollback_error(SQL);
            }
        } finally {
            as.close();
        }
        return null;
    }

}
