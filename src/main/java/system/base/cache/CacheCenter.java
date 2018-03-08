package system.base.cache;

import java.util.logging.Level;
import java.util.logging.Logger;
import system.db.Service;

/**
 *
 * @author jweb
 */
public class CacheCenter {

    private int doIndex;    //变更标识
    private int readIndex;  //读取标识
    private Service dbs;
    private static Service dbS;
    private boolean reloadByNow = false;
    private CacheData cacheData;

    /**
     * 缓存中心
     *
     * @param cd 指定继承 CacheData 的类型
     * @param dbs jdbc操作实例的封装
     */
    public CacheCenter(Class<? extends CacheData> cd, Service dbs) {

        this.doIndex = 1;
        this.readIndex = 0;
        this.dbs = null == dbs ? dbS : dbs;
        try {
            this.cacheData = cd.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CacheCenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (this.reloadByNow) {
            if (readIndex != doIndex) {
                cacheData.loadData(dbs);
                readIndex = doIndex;//同步版本
            }
        }
    }

    /**
     * 缓存中心
     *
     * @param cd 指定继承 CacheData 的类型
     */
    public CacheCenter(Class<? extends CacheData> cd) {
        this.doIndex = 1;
        this.readIndex = 0;
        this.dbs = null == dbs ? dbS : dbs;
        try {
            this.cacheData = cd.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CacheCenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 取得缓存数据
     *
     * @param <T> 泛型
     * @param t 泛型xx的类型
     * @return xx的实例
     */
    public <T> T getCacheData(Class<T> t) {
        reloadData();
        return (T) this.cacheData;
    }

    /**
     * 返回实现CacheData的实例对象
     *
     * @return CacheData
     */
    public CacheData getCacheData() {
        reloadData();
        return this.cacheData;
    }

    /**
     * 加载数据
     */
    synchronized public void reloadData() {
        if (readIndex != doIndex) {
            cacheData.loadData(dbs);
            readIndex = doIndex;//同步版本
        }
    }

    /**
     * 设置jdbc操作对象的实例
     *
     * @param dbs jdbc对象实例
     */
    public static void SetDB(system.db.Service dbs) {
        CacheCenter.dbS = dbs;
    }

    /**
     * 马上重置（并加载数据）
     */
    public void resetNow() {
        cacheData.loadData(dbs);
        readIndex = doIndex;//同步版本;
    }

    /**
     * 重置
     */
    public void reset() {
        doIndex();
    }

    /**
     * 控制版本
     */
    private void doIndex() {
        //版本变更
        doIndex = doIndex > 65534 ? 0 : doIndex + 1;
    }

}
