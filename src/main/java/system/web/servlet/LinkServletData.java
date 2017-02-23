package system.web.servlet;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import system.web.XY_Instruction;
import system.base.annotation.JWFilter;
import system.web.filter.chain.config.LinkFilters;
import system.base.annotation.Validate;
import system.web.validate.model.InitValidateModel;
import system.web.validate.model.ValidateModel;

/**
 *
 * @author wangchunzi
 */
final public class LinkServletData {

    private static final Map<String, ServletModel> requestURLMap = new HashMap();
    private static boolean openServlet = false;

    private final system.web.hm.model.LinkHMData hp = new system.web.hm.model.LinkHMData();
    private final InitValidateModel validate = new InitValidateModel();
    system.base.log.SysLog log = new system.base.log.SysLog();
    private final system.web.filter.chain.InitJWFilterModel filterObject = new system.web.filter.chain.InitJWFilterModel();

    public static boolean isOpenServlet() {
        return openServlet;
    }

    //-------------------初始化数据---------------------------------
    public static ServletModel getLinkModel(final String servletURL) {
        return requestURLMap.get(servletURL);
    }

    ValidateModel vm = null; //校验对象的数据中心
    int validate_instruction = 0;//校验指令
//    int method_instruction = 0;//方法指令（静态方法=16，对象方法=0）

    public void DoH(Class c, LinkFilters lf, String servletMapPath) throws InstantiationException, IllegalAccessException {
        String[] urlPatterns = null;
        if ((HttpServlet.class.isAssignableFrom(c) && HttpServlet.class != c)
                || (Servlet.class.isAssignableFrom(c) && Servlet.class != c)) {
            filterObject.clear();
            vm = null; //校验对象的数据中心 校验对象初始化
            validate_instruction = 0;//校验指令 

            //执行过滤器判断
            if (null != c.getAnnotation(JWFilter.class)) {
                //执行标注在类的过滤器处理
                filterObject.iniFilterModel_H(((JWFilter) c.getAnnotation(JWFilter.class)).value());
            }
            //执行过滤器判断
            if (null != c.getAnnotation(Validate.class)) {
                Validate at_Validate = (Validate) c.getAnnotation(Validate.class);
                if (null != at_Validate && ValidateModel.class.isAssignableFrom(at_Validate.value())) {
                    validate.doinitData(at_Validate.value());
                    vm = validate.get(at_Validate.value());
                    validate_instruction = XY_Instruction.VALIDATE;
                }
            }

            if (null != c.getAnnotation(WebServlet.class)) {
                WebServlet web = (WebServlet) c.getAnnotation(WebServlet.class);
                urlPatterns = web.urlPatterns();
            }
            //对原生的servlet执行行全局检查
            filterObject.iniGrobalConfiguration(lf.getResource(servletMapPath, c.getName()));
            //执行方法过滤器(全局集合+@H集合=方法集合。)
            filterObject.iniFilterModel_M(null);

            if (null != urlPatterns) {
                for (String url : urlPatterns) {
                    /**
                     *
                     * @param fmTop 执行顶部过滤链
                     * @param vm 执行校验
                     * @param fmCenter 执行中部过滤链
                     * @param fmButtom 执行底部过滤链
                     * @param instruction 指令1-15.
                     */
                    requestURLMap.put(url, new ServletModel(
                            filterObject.getHMFilterModel_TOP()//顶部过滤链
                            , vm //执行校验
                            , filterObject.getHMFilterModel_CENTER()//中部过滤链
                            , filterObject.getHMFilterModel_BUTTOM() //底部过滤链
                            , filterObject.getFilterInstruction() + validate_instruction//KEY
                    ));
                    openServlet = true;
                    log.putLog(1, c.getName() + "映射URL ： " + url + "//指令：" + (filterObject.getFilterInstruction() + validate_instruction));
                }
            }
            log.println();
        }
    }
}
