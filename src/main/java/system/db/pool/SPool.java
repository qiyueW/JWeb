package system.db.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import system.db.config.RunCDBConfig;

/**
 *
 * @author wangchunzi
 */
final public class SPool {

    private final RunCDBConfig rc;

    public SPool(RunCDBConfig rc) {
        this.rc = rc;
        iniMaxConnection(rc.connection_max_s);
    }
    private int index = 1;//游标
    private int waitThread = 0;//等待的线程
    private final Map<Integer, SSession> map = new HashMap();

    public SSession getSSession() {
        SSession ic = getIC();
        return null != ic ? ic : doWait();
    }
    
    /**
     * 初始化最大连接池
     *
     * @param count
     */
    private void iniMaxConnection(int count) {
        Connection conn;
        for (int i = 1; i <= count; i++) {
            conn = this.rc.getConnection();
            map.put(i, new SSession(i, conn));
        }
    }

    /**
     * 不锁，可让多线程进行等待。
     * <p>
     * 进入等待队列.队列外，直接null
     */
    private SSession doWait() {
        if (waitThread < rc.resource_max_wait_thread_adu) {//在请允许等待里，进行等等
            //假如在此点线程类变量还没++，并发的线程可能也会加入队列（即使刚刚已经达到最大限制）。
            //旦情况一般不会太恐怖（这个ConnPool类仅作用于增、删、改。）。既有一定的小波动。
            ++waitThread;
            try {
//                System.out.println("Thread.sleep(1000) start.....");
                Thread.sleep(rc.resource_max_wait_time_adu);//睡一会
//                System.out.println("Thread.sleep(1000) end.....");
                SSession ic = getIC();//再次进入检测
                if (null != ic) {
                    --waitThread;
//                    System.out.println("睡觉后，重新获得连接啦！！");
//                    sys.wx.m.db.pool.test.TestC.resetConnection();
//                    ic.unlock = true;
                    return ic;
                }
                return null;
            } catch (InterruptedException ex) {
            }
        }
        return null;
    }

    /**
     * 锁
     *
     * @return
     */
    synchronized private SSession getIC() {
        index = index > rc.connection_max_adu ? 1 : index;
        SSession ic = map.get(index);
        if (ic.son < rc.connection_son_max_s) {
            return ic;
        }
        index++;
        index = index > rc.connection_max_adu ? 1 : index;
        ic = map.get(index);
        if (ic.son < rc.connection_son_max_s) {
            return ic;
        }
        index++;
        index = index > rc.connection_max_adu ? 1 : index;
        ic = map.get(index);
        if (ic.son < rc.connection_son_max_s) {
            return ic;
        }
        index++;
        index = index > rc.connection_max_adu ? 1 : index;
        ic = map.get(index);
        if (ic.son < rc.connection_son_max_s) {
            return ic;
        }
        index++;
        index = index > rc.connection_max_adu ? 1 : index;
        ic = map.get(index);
        if (ic.son < rc.connection_son_max_s) {
            return ic;
        }
        index++;
        index = index > rc.connection_max_adu ? 1 : index;
        ic = map.get(index);
        if (ic.son < rc.connection_son_max_s) {
            return ic;
        }
        index++;
        index = index > rc.connection_max_adu ? 1 : index;
        ic = map.get(index);
        if (ic.son < rc.connection_son_max_s) {
            return ic;
        }
        for (int i = 1; i <= rc.connection_max_s; i++) {
            if (ic.son < rc.connection_son_max_s) {
                return ic;
            }
        }
        return null;
    }

    /**
     * 意外事故关闭时，重新初始化连接
     *
     * @param icon
     * @return ADUSession
     */
    public SSession resetConnection(SSession icon) {
        icon = new SSession(icon.getIndex(), this.rc.getConnection());
        map.replace(icon.getIndex(), icon);
        return icon;
    }

    /**
     * new ConnPool().closeOne(X);真正关闭一个指定X坐标的
     *
     * @param index
     */
    public void closeOne(Integer index) {
        Connection cc = map.get(index).getConn();
        try {
            cc.close();
        } catch (SQLException ex) {
        }
        map.remove(index);
    }

    public void closeALL() {
        Connection cc;
        for (Map.Entry<Integer, SSession> entry : map.entrySet()) {
            cc = entry.getValue().getConn();
            try {
                if (null != cc && !cc.isClosed()) {
                    cc.close();
                }
            } catch (SQLException ex) {
            }
        }
    }

}
