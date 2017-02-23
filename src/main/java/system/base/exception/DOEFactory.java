package system.base.exception;

/**
 *
 * @author wangchunzi
 */
final public class DOEFactory {

    private static DOEConfiguration doeObj = new DOEConfigurationDefault();

    final static public DOEConfiguration getDOE() {
        return doeObj;
    }
    
    public static void set(Class<? extends DOEConfiguration> doe) {
        try {
            DOEFactory.doeObj = doe.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            doeObj = new DOEConfigurationDefault();
            System.err.println("执行自定数据库异常处理对象实例出错。系统将采用默认处理!");
        }
    }
}
