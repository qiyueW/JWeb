package system.db.dao.adus;

import java.util.List;

/**
 *
 * @author wangchunzi
 */
public interface ADUSDao {

    /**
     * 执行查询 一条记录
     *
     * @param <T>
     * @param c
     * @param sql
     * @return
     */
    public <T> T executeQueryOne(Class<T> c, final String sql);

    /**
     * 执行查询 多条记录
     *
     * @param <T>
     * @param c
     * @param sql
     * @return
     */
    public <T> List<T> executeQueryVast(Class<T> c, final String sql);

    /**
     * 统计
     *
     * @param sql
     * @return
     */
    public int executeQueryCount(final String sql);

    public int executeUpdate(final String sql);

    public int[] executeBatch(final String... sql);
}
