package system.base.exception;

/**
 *
 * @author wangchunzi
 */
public abstract class DOEConfiguration {
    
    public abstract void newInstance(final String sql);
    public abstract void executeQuery(final String sql);
    
    public abstract void executeUpdate(final String sql);
    public abstract void executeUpdate_rollback_success(final String sql);
    public abstract void executeUpdate_rollback_error(final String sql);
    
    public abstract void executeBatch(final String[] sql);
    public abstract void executeBatch_rollback_success(final String[] sql);
    public abstract void executeBatch_rollback_error(final String[] sql);

}
