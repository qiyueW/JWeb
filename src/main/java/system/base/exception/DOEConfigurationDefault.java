package system.base.exception;

/**
 *
 * @author wangchunzi
 */
public class DOEConfigurationDefault extends DOEConfiguration {

    /**
     * 添加数据发生异常 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    @Override
    public void newInstance(String sql) {
        System.err.println("执行查询-实例装箱容器时发生错误。" + sql);
    }

    /**
     * 查询数据发生异常时 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    @Override
    public void executeQuery(String sql) {
        System.err.println("执行查询操作时，发生异常" + sql);
    }

    /**
     * 更新数据发生异常时 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    @Override
    public void executeUpdate(String sql) {
        System.err.println("执行增、删、改操作时，发生异常 " + sql);
    }

    /**
     * 更新数据_并回滚成功时 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    @Override
    public void executeUpdate_rollback_error(String sql) {
        System.err.println("执行增、删、改操作时，发生异常,并回滚出错!!!!! " + sql);
    }

    /**
     * 更新数据_并回滚失败时 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    @Override
    public void executeUpdate_rollback_success(String sql) {
        System.err.println("执行增、删、改操作时，发生异常。回滚成功 " + sql);
    }

    /**
     * 批处理 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    @Override
    public void executeBatch(String[] sql) {
        String str = "";
        for (String s : sql) {
            str = str + s + "\n";
        }
        System.err.println("执行增、删、改批处理时，发生异常 " + str);
    }

    /**
     * 回滚成功 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    @Override
    public void executeBatch_rollback_success(String[] sql) {
        String str = "";
        for (String s : sql) {
            str = str + s + "\n";
        }
        System.err.println("执行增、删、改批处理时，发生异常。回滚成功 " + str);
    }

    /**
     * 回滚出错 当用户操作数据库发生异常时，调用此方法。
     *
     * @param sql 用户操作执行数据库操作的语句
     */
    @Override
    public void executeBatch_rollback_error(String[] sql) {
        String str = "";
        for (String s : sql) {
            str = str + s + "\n";
        }
        System.err.println("执行增、删、改批处理时，发生异常，并回滚失败！！！！！！！！ " + str);
    }

}
