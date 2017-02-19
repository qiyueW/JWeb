package system.web;

import system.web.hm.annotation.M;
import system.web.hm.annotation.H;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import system.web.filter.annotation.JWFilter;
import system.web.filter.chain.config.ConfigurationFilter;
import system.web.filter.chain.config.ConfigurationFilterService;
import system.web.filter.chain.config.LinkFilters;
import system.web.hm.model.LinkHMModel;
import system.web.servlet.Servlet;
import system.web.validate.model.InitValidateModel;
import system.web.validate.annotation.Validate;
import system.web.validate.model.ValidateModel;

/**
 *
 * @author ik
 */
public class InitLinkData {

    private final system.web.hm.model.LinkHMData hp = new system.web.hm.model.LinkHMData();
    private final system.web.servlet.LinkServletData servlet = new system.web.servlet.LinkServletData();

    private final InitValidateModel validate = new InitValidateModel();
    system.base.log.SysLog log = new system.base.log.SysLog();
    private final system.web.filter.chain.InitJWFilterModel filterObject = new system.web.filter.chain.InitJWFilterModel();

    Method[] fs;
    String url = ""; //存入@H+@M的完整路径
    M at_M;//@M的数据
    Validate at_Validate;//@V的数据
    ValidateModel vm = null; //校验对象的数据中心
    int validate_instruction = 0;//校验指令
//    int method_instruction = 0;//方法指令（静态方法=16，对象方法=0）

    public void doinitHMData(List<Class> cs) {
        ConfigurationFilterService fconfig = new ConfigurationFilterService();

        for (Class ffc : cs) {
            if (ConfigurationFilter.class.isAssignableFrom(ffc) && ConfigurationFilter.class != ffc) {
                fconfig.iniUserConfig(ffc);
                break;
            }
        }
        LinkFilters lf = fconfig.getLinkFilters();

        log.setLogTitle("初始化服务请求资源");
        for (Class c : cs) {
            try {
                if ((HttpServlet.class.isAssignableFrom(c) && HttpServlet.class != c)
                || (Servlet.class.isAssignableFrom(c) && Servlet.class != c)){
                    servlet.DoH(c, lf, url);
                } else {
                    this.DoH(c, lf);
                }
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(InitLinkData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        log.println();
    }

    private void DoH(Class c, LinkFilters lf) throws InstantiationException, IllegalAccessException {

        if (null != c.getAnnotation(H.class)) {
            filterObject.clear();
            fs = c.getMethods();
            url = ""; //存入@H+@M的完整路径 初始化
            
            validate_instruction = 0;//校验指令 

            //执行过滤器判断
            if (null != c.getAnnotation(JWFilter.class)) {
                //执行标注在类的过滤器处理
                filterObject.iniFilterModel_H(((JWFilter) c.getAnnotation(JWFilter.class)).value());
            }
            //执行方法过滤器判断
            String hV = ((H) c.getAnnotation(H.class)).value().trim();

            for (Method f : fs) {
                vm = null; //校验对象的数据中心 校验对象初始化
                at_M = (M) f.getAnnotation(M.class);
                at_Validate = (Validate) f.getAnnotation(Validate.class);

                if (null != at_M && !getMValueOrURL(at_M).isEmpty()) {

                    url = requestURL(hV, getMValueOrURL(at_M), WebContext.getWebContext().webConfig.HM_SUFFIX);

                    //对@H@M的，执行全局检查
                    filterObject.iniGrobalConfiguration(lf.getResource(url, c.getName() + "." + f.getName()));

                    //执行方法过滤器(全局集合+@H集合=方法集合。)
                    filterObject.iniFilterModel_M(
                            null != f.getAnnotation(JWFilter.class)
                            ? ((JWFilter) f.getAnnotation(JWFilter.class)).value()
                            : null
                    );
                    if (null != at_Validate && ValidateModel.class.isAssignableFrom(at_Validate.value())) {
                        validate.doinitData(at_Validate.value());
                        vm = validate.get(at_Validate.value());
                        validate_instruction = XY_Instruction.VALIDATE;
                    }

                    /**
                     *
                     * @param fm1 执行前置过滤链
                     * @param vm 执行校验
                     * @param fm2 执行后置过滤链
                     * @param hClass 执行用户请求关连的类
                     * @param method 执行用户请求的关连类下的方法
                     * @param return_way
                     * 执行完毕，返回方式。使用方法返回的值：如果返回值为null，无视此参数作用。如果有值，
                     * @param instruction 指令1-15.
                     */
                    hp.initPutHMModel(url, new LinkHMModel(
                            filterObject.getHMFilterModel_TOP()//顶部过滤链
                            , vm //执行校验
                            , filterObject.getHMFilterModel_CENTER()//中部过滤链
                            , c //执行用户请求关连的类
                            , f //执行用户请求的关连类下的方法
                            , this.isStaticM(f) //                            , at_M.return_way()
                            , filterObject.getHMFilterModel_BUTTOM() //底部过滤链
                            , filterObject.getFilterInstruction() + validate_instruction//KEY
                    ));
                    log.putLog(1, c.getName() + "." + f.getName() + "映射URL ： " + url + "//指令：" + (filterObject.getFilterInstruction() + validate_instruction));

                }
            }
        }
    }

    private String getMValueOrURL(M m) {
        return m.url().isEmpty() ? m.value() : m.url();
    }

    /**
     * 请求的后续。如果没有，则不设。如果有
     *
     * @param hz
     * @return
     */
    private String requestURL(String hurl, String murl, String hz) {
        if (!hurl.isEmpty()) {
            hurl = hurl.startsWith("/") ? hurl : "/" + hurl;
            hurl = hurl.endsWith("/") ? hurl.substring(0, hurl.length() - 1) : hurl;
        }
        murl = murl.startsWith("/") ? murl : "/" + murl;
        murl = murl.endsWith("/") ? murl.substring(0, murl.length() - 1) : murl;
        return hurl + murl + (null == hz ? "" : hz);
    }

    private boolean isStaticM(Method m) {
        return m.getParameterCount() == 1 && m.getParameterTypes()[0].equals(JWeb.class);
    }
}
