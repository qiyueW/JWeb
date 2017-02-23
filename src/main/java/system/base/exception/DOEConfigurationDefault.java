package system.base.exception;

/**
 *
 * @author wangchunzi
 */
public class DOEConfigurationDefault extends DOEConfiguration {

    @Override
    public void newInstance(String sql) {
        System.out.println("执行查询-实例装箱容器时发生错误。" + sql);
    }

    @Override
    public void executeQuery(String sql) {
        System.out.println("执行查询操作时，发生异常" + sql);
    }

    @Override
    public void executeUpdate(String sql) {
        System.out.println("执行增、删、改操作时，发生异常 " + sql);
    }

    @Override
    public void executeUpdate_rollback_error(String sql) {
        System.out.println("执行增、删、改操作时，发生异常,并回滚出错!!!!! " + sql);
    }

    @Override
    public void executeUpdate_rollback_success(String sql) {
        System.out.println("执行增、删、改操作时，发生异常。回滚成功 " + sql);
    }

    @Override
    public void executeBatch(String[] sql) {
        String str = "";
        for (String s : sql) {
            str = str + s + "\n";
        }
        System.out.println("执行增、删、改批处理时，发生异常 " + str);
    }

    @Override
    public void executeBatch_rollback_success(String[] sql) {
        String str = "";
        for (String s : sql) {
            str = str + s + "\n";
        }
        System.out.println("执行增、删、改批处理时，发生异常。回滚成功 " + str);
    }

    @Override
    public void executeBatch_rollback_error(String[] sql) {
        String str = "";
        for (String s : sql) {
            str = str + s + "\n";
        }
        System.out.println("执行增、删、改批处理时，发生异常，并回滚失败！！！！！！！！ " + str);
    }

}
