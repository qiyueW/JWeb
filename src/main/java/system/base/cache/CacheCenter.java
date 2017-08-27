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

    public <T> T getCacheData(Class<T> t) {
        reloadData();
        return (T) this.cacheData;
    }

    public CacheData getCacheData() {
        reloadData();
        return this.cacheData;
    }

    synchronized public void reloadData() {
        if (readIndex != doIndex) {
            cacheData.loadData(dbs);
            readIndex = doIndex;//同步版本
        }
    }

    public static void SetDB(system.db.Service dbs) {
        CacheCenter.dbS = dbs;
    }

    public void resetNow() {
        cacheData.loadData(dbs);
        readIndex = doIndex;//同步版本;
    }

    public void reset() {
        doIndex();
    }

    private void doIndex() {
        //版本变更
        doIndex = doIndex > 65534 ? 0 : doIndex + 1;
    }

}
