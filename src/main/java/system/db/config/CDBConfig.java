package system.db.config;

/**
 *
 * @author wangchunzi
 */
public class CDBConfig {
    
    public String driver = "com.mysql.jdbc.Driver";

    public String dburl;

    public String username;

    public String password;

    /**
     * 等待连接资源超时时间
     */
    public int connection_max_adu=8;//MAX_CONNECTION;
    /**
     * 连接操作超时时间
     */
    public int connection_timeout_adu=100;//MAX_CONNECTION;
    /**
     * 允许最大的人数等待连接资源
     */
    public int resource_max_wait_thread_adu=100;//
    /**
     * 等待连接资源超时时间
     */
    public long resource_max_wait_time_adu=200;
    /**
     * 连接库最大连接数
     */
    public int connection_max_s=10;//MAX_CONNECTION;
    /**
     * 连接操作超时时间
     */
    public int connection_timeout_s=50;//MAX_CONNECTION;
    /**
     * 允许最大的人数等待连接资源
     */
    public int resource_max_wait_thread_s=100;//
    /**
     * 等待连接资源超时时间
     */
    public long resource_max_wait_time_s=100;

    /**
     * 一个连接最大可以产生出多少个子连接
     */
    public int connection_son_max_s=150;//MAX_CONNECTION;
    
}
