//package system.db.pool;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author IK
// */
//public class IndexConnectionS {
//
//    public IndexConnectionS(Integer index, Connection conn) {
//        this.index = index;
//        this.conn = conn;
//    }
//    private Integer index = null;
//    public int son = 0;
//    private Connection conn = null;
//
//    public Integer getIndex() {
//        return index;
//    }
//
//    public Connection getConn() {
//        return conn;
//    }
//    
//    public Statement getStatement() {
//        try {
//            son++;
//            return conn.createStatement();
//        } catch (SQLException ex) {
//            Logger.getLogger(IndexConnectionS.class.getName()).log(Level.SEVERE, null, ex);
//            son--;
//        }
//        return null;
//    }
//
//    public void close(Statement state) {
//        son--;
//        try {
//            state.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(IndexConnectionS.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//}
