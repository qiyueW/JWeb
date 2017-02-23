package system.db;

import system.db.dao.ADUS;
import system.db.dao.AddDaoImp;
import system.db.dao.DeleteDaoImp;
import system.db.dao.SelectDaoImp;
import system.db.dao.UpdateDaoImp;
import system.db.sql.SQL;
import system.db.pool.ADUPool;
import system.db.pool.SPool;

/**
 *
 * @author wangchunzi
 */
final public class Service {

    public final SQL SQL;
    public final ADUS ADUS;
    public final AddDaoImp A;
    public final DeleteDaoImp D;
    public final SelectDaoImp S;
    public final UpdateDaoImp U;

    public Service(ADUPool adupool, SPool spool) {
        SQL = new SQL();
        this.ADUS = new ADUS(adupool, spool);
        this.A = new AddDaoImp(this.ADUS, this.SQL);
        this.D = new DeleteDaoImp(this.ADUS, this.SQL);
        this.S = new SelectDaoImp(this.ADUS, this.SQL);
        this.U = new UpdateDaoImp(this.ADUS, this.SQL);
    }
}
