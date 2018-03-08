package system.base.exception;

/**
 *
 * @author wangchunzi
 */
public abstract class DOEConfiguration {

    /**
     *添加数据发生异常 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    public abstract void newInstance(final String sql);

    /**
     * 查询数据发生异常时 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    public abstract void executeQuery(final String sql);

    /**
     * 更新数据发生异常时 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    public abstract void executeUpdate(final String sql);

    /**
     * 更新数据_并回滚成功时 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    public abstract void executeUpdate_rollback_success(final String sql);

    /**
     * 更新数据_并回滚失败时 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    public abstract void executeUpdate_rollback_error(final String sql);

    /**
     * 批处理 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    public abstract void executeBatch(final String[] sql);

    /**
     * 回滚成功 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    public abstract void executeBatch_rollback_success(final String[] sql);

    /**
     * 回滚出错 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    public abstract void executeBatch_rollback_error(final String[] sql);

}
