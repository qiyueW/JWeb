package system.web.hm.model;

import java.lang.reflect.Method;
import system.web.filter.chain.FilterModel;
import system.web.validate.model.ValidateModel;

/**
 *
 * @author wangchunzi
 */
public class LinkHMModel {

    /**
     *
     * @param fmTop 执行顶部过滤链
     * @param vm 执行校验
     * @param fmCenter 执行中部过滤链
     * @param hClass 执行用户请求关连的类
     * @param method 执行用户请求的关连类下的方法
     * @param isStaticMethod
//     * @param return_way 执行完毕，返回方式。使用方法返回的值：如果返回值为null，无视此参数作用。如果有值，
     * @param fmButtom 执行底部过滤链
     * @param instruction 指令1-15.
     */
    public LinkHMModel(
            FilterModel[] fmTop, ValidateModel[] vm, FilterModel[] fmCenter, Class hClass, Method method, boolean isStaticMethod
            , FilterModel[] fmButtom, int instruction
    ) {
        this.hClass = hClass;
        this.method = method;
        this.fmTop = fmTop;
        this.fmCenter = fmCenter;
        this.vm = vm;
        this.fmButtom = fmButtom;
        this.instruction = instruction;
        this.isStaticMethod = isStaticMethod;
//        if (null != fmTop) {
//            for (FilterModel fm : fmTop) {
//                System.out.println(fm.getClass().getName());
//            }
//        }
//        if (null != fmCenter) {
//            for (FilterModel fm : fmCenter) {
//                System.out.println(fm.getClass().getName());
//            }
//        }
//        if (null != fmButtom) {
//            for (FilterModel fm : fmButtom) {
//                System.out.println(fm.getClass().getName());
//            }
//        }
    }

    public final Class hClass;
    public final Method method;

    public final FilterModel[] fmTop;
    public final ValidateModel[] vm;
    public final FilterModel[] fmCenter;
    public final FilterModel[] fmButtom;
    public final boolean isStaticMethod;
    /**
     * <p>
     * 转发数据请求方法。分两种：
     * 
     * <p>
     * 1.FORWARD(服务器端，转发其他web处理类进行处理客户端请求)
     * 
     * <p>
     * 2.REDIRECT(通知浏览器访问xx路径，只可URL带参)
     * 
     */
//    public final int SEND_URL_WAY;

    /**
     * 原计算出来的指令，+8，则代表以静态方法执行用户请求。
     */
    public final int instruction;

}
