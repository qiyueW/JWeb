package system.db;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
	private static final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

	public DBEngine(CDBConfig c) {
		this.rc = new RunCDBConfig(c);
		this.adupool = new ADUPool(this.rc);
		this.spool = new SPool(this.rc);
		this.service = new Service(this.adupool, this.spool);

		if (c.openDenyCloseConnectionCheck){
			ses.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					adupool.denyAutoClose();
					spool.denyAutoClose();
				}
			}, 0, c.denyCloseConnectionCheck, TimeUnit.SECONDS);
		}
	}

	@Override
	public void finalize() {
		System.out.println("关闭连接池......");

		this.adupool.closeALL();
		this.spool.closeALL();
		ses.shutdownNow();
		
		System.gc();
	}
}