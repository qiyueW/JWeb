package system.db;

import system.db.config.CDBConfig;
import system.db.config.RunCDBConfig;
import system.db.pool.ADUPool;
import system.db.pool.SPool;

/**
 *
 * @author wangchunzi
 */
final public class DBEngine {

    private final RunCDBConfig rc;
    private final ADUPool adupool;
    private final SPool spool;
    public final Service service;

    public DBEngine(CDBConfig c) {
        this.rc = new RunCDBConfig(c);
        this.adupool = new ADUPool(this.rc);
        this.spool = new SPool(this.rc);
        this.service = new Service(this.adupool, this.spool);
    }
    
    public final void closePoolConnection(){
    	this.adupool.closeALL();
    	this.spool.closeALL();
    }

}
