package system.db.sql;

/**
 *
 * @author wangchunzi
 */
public interface Common {

    /**
     * 直接执行sql语句(增加、修改、删除)
     *
     * @param sql sql语句
     * @return int
     */
    public int executeUpdate(final String sql);
}
