package system.base.exception;

/**
 *
 * @author wangchunzi
 */
final public class DOEFactory {

    /**
     * 默认处理实例
     */
    private static DOEConfiguration doeObj = new DOEConfigurationDefault();

    /**
     * 取得默认处理实例
     *
     * @return DOEConfiguration
     */
    final static public DOEConfiguration getDOE() {
        return doeObj;
    }

    /**
     * 检出用户自定义处理jdbc操作异常的相关类，并实例它
     *
     * @param doe  extends DOEConfiguration 的类型
     */
    public static void set(Class<? extends DOEConfiguration> doe) {
        try {
            DOEFactory.doeObj = doe.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            doeObj = new DOEConfigurationDefault();
            System.err.println("执行自定数据库异常处理对象实例出错。系统将采用默认处理!");
        }
    }
}
