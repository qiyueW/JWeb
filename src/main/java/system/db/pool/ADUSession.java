package system.db.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IK
 */
public class ADUSession {

    public ADUSession(Integer index, Connection conn) {
        this.index = index;
        this.conn = conn;
        try {
            this.conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(ADUSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public int executeUpdateCMD(final String sql) {
//        System.out.println(sql);
//        try {
//            Statement cs = this.conn.createStatement();
//            int i = cs.executeUpdate(sql);
//            this.conn.commit();
//            cs.close();
//            return i;
//        } catch (SQLException ex) {
//            try {
//                this.conn.rollback();
//            } catch (SQLException ex1) {
//                Logger.getLogger(ADUSession.class.getName()).log(Level.SEVERE, null, ex1);
//            }
//        } finally {
//            this.unlock = true;
//        }
//        return 0;
//    }

    private Integer index = null;
    private Connection conn = null;
    private boolean unlock = true;

    boolean isUnLock() {
        return this.unlock;
    }

    public void close() {
        this.unlock = true;
    }

    void Lock() {
        this.unlock = false;
    }

    public Integer getIndex() {
        return index;
    }

    public Connection getConn() {
        return conn;
    }
}
