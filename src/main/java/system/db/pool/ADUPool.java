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
final public class ADUPool {

    private final RunCDBConfig rc;

    public ADUPool(RunCDBConfig rc) {
        this.rc = rc;
        iniMaxConnection(rc.connection_max_adu);
    }

    private int index = 1;//游标
    private int waitThread = 0;//等待的线程
    private final Map<Integer, ADUSession> map = new HashMap();

    public ADUSession getIndexConnection() {
        ADUSession ic = getIC();
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
            map.put(i, new ADUSession(i, conn));
        }
    }

    /**
     * 不锁，可让多线程进行等待。
     * <p>
     * 进入等待队列.队列外，直接null
     */
    private ADUSession doWait() {
        if (waitThread < rc.resource_max_wait_thread_adu) {//在请允许等待里，进行等等
            //假如在此点线程类变量还没++，并发的线程可能也会加入队列（即使刚刚已经达到最大限制）。
            //旦情况一般不会太恐怖（这个ConnPool类仅作用于增、删、改。）。既有一定的小波动。
            ++waitThread;
            try {
                Thread.sleep(rc.resource_max_wait_time_adu);//睡一会
//                System.out.println("Thread.sleep(1000) end.....");
                ADUSession ic = getIC();//再次进入检测
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
    synchronized private ADUSession getIC() {
        index = index > rc.connection_max_adu ? 1 : index;
        ADUSession ic = map.get(index);
        if (ic.isUnLock()) {                                 //1-3
            ic.Lock();
            index++;
            return ic;
        } else {
            index++;//另一个ic住的房号                  //2-4
            //检查下一个ic是否住在我们管辖的范围内,如果不是，那么回到我们的1编号房处
            index = index > rc.connection_max_adu ? 1 : index;
            ic = map.get(index);
            //检查这间ic是否还能生儿子。满足的话，请ic出手。并通知领导当前编号房间可以出手
            if (ic.isUnLock()) {                                 //1-3
                ic.Lock();
                index++;//送走ic,通知下一个ic的编号是index++
                return ic;
            }
            //一间一间找
            for (int i = 1; i <= rc.connection_max_adu; i++) {
                ic = map.get(i);
                if (ic.isUnLock()) {                                 //1-3
                    ic.Lock();
                    index = ++i;//送走ic,通知下一个ic的编号是(++i)
                    return ic;
                }
            }
            return null;
        }
    }

    /**
     * 意外事故关闭时，重新初始化连接
     *
     * @param icon
     * @return ADUSession
     */
    public ADUSession resetConnection(ADUSession icon) {
        icon = new ADUSession(icon.getIndex(), this.rc.getConnection());
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
        StringBuilder sb=new StringBuilder();
        
        for (Map.Entry<Integer, ADUSession> entry : map.entrySet()) {
            cc = entry.getValue().getConn();
            try {
                if (null != cc && !cc.isClosed()) {
                    cc.close();
                    sb.append("\nADU:成功关闭一个连接")
                    .append(cc)
                    .append("//the index: ")
                    .append(entry.getKey());
                    
                }
            } catch (SQLException ex) {
            }
        }
        System.out.println(sb.toString());
        System.gc();
    }

}
