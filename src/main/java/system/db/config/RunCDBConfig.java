package system.db.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final public class RunCDBConfig {

    public final String driver;
    public final String dburl;
    public final String username;
    public final String password;
    /**
     * 等待连接资源超时时间
     */
    public final int connection_max_adu;//MAX_CONNECTION;
    /**
     * 连接操作超时时间
     */
    public final int connection_timeout_adu;//MAX_CONNECTION;
    /**
     * 允许最大的人数等待连接资源
     */
    public final int resource_max_wait_thread_adu;//
    /**
     * 等待连接资源超时时间
     */
    public final long resource_max_wait_time_adu;
    /**
     * 连接库最大连接数
     */
    public final int connection_max_s;//MAX_CONNECTION;
    /**
     * 连接操作超时时间
     */
    public final int connection_timeout_s;//MAX_CONNECTION;
    /**
     * 允许最大的人数等待连接资源
     */
    public final int resource_max_wait_thread_s;//
    /**
     * 等待连接资源超时时间
     */
    public final long resource_max_wait_time_s;

    /**
     * 一个连接最大可以产生出多少个子连接
     */
    public final int connection_son_max_s;//MAX_CONNECTION;

    public RunCDBConfig(CDBConfig c) {
        this.driver = c.driver;

        this.dburl = c.dburl;

        this.username = c.username;

        this.password = c.password;

        /**
         * 等待连接资源超时时间
         */
        this.connection_max_adu = c.connection_max_adu;//MAX_CONNECTION;
        /**
         * 连接操作超时时间
         */
        this.connection_timeout_adu = c.connection_timeout_adu;//MAX_CONNECTION;
        /**
         * 允许最大的人数等待连接资源
         */
        this.resource_max_wait_thread_adu = c.resource_max_wait_thread_adu;//
        /**
         * 等待连接资源超时时间
         */
        this.resource_max_wait_time_adu = c.resource_max_wait_time_adu;
        /**
         * 连接库最大连接数
         */
        this.connection_max_s = c.connection_max_s;//MAX_CONNECTION;
        /**
         * 连接操作超时时间
         */
        this.connection_timeout_s = c.connection_timeout_s;//MAX_CONNECTION;
        /**
         * 允许最大的人数等待连接资源
         */
        this.resource_max_wait_thread_s = c.resource_max_wait_thread_s;//
        /**
         * 等待连接资源超时时间
         */
        this.resource_max_wait_time_s = c.resource_max_wait_time_s;

        /**
         * 一个连接最大可以产生出多少个子连接
         */
        this.connection_son_max_s = c.connection_son_max_s;//MAX_CONNECTION
    }

    public Connection getConnection() {
        try {
            Class.forName(this.driver);
            return DriverManager.getConnection(dburl, username, password);
        } catch (ClassNotFoundException ex) {
            System.err.println("加载连接驱动失败：" + this.driver);
        } catch (SQLException ex) {
            System.err.println("连接数据库失败：" + this.dburl + "//" + this.username + "//" + this.password);
        }
        return null;
    }
}
