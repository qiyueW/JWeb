package system.db.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wangchunzi
 */
public class SSession {

    int son;
    private Integer index = null;
    private Connection conn = null;

    SSession(int i, Connection conn) {
        this.index = i;
        this.conn = conn;
    }

    public Integer getIndex() {
        return index;
    }

    public Connection getConn() {
        return conn;
    }
    
    public Statement getStatement() {
        try {
            son++;
            return conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(SSession.class.getName()).log(Level.SEVERE, null, ex);
            son--;
        }
        return null;
    }

    public void close(Statement state) {
        son--;
        try {
            state.close();
        } catch (SQLException ex) {
            Logger.getLogger(SSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
