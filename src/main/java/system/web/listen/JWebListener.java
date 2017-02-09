package system.web.listen;

import java.util.EnumSet;
import java.util.regex.Pattern;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import system.web.WebContext;
import system.web.filter.resource.ConfigurationResource;
import system.web.filter.resource.ResourceService;

/**
 *
 * @author wangchunzi
 */
@WebListener
public class JWebListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("容器初始化了！！！！！！！！love you!");

        system.base.Sc.ScF scf = new system.base.Sc.ScF();
        new system.web.IniTempMain().iniHMModel(
                sce.getServletContext().getRealPath("/"), sce.getServletContext().getContextPath(), scf
        );

        EnumSet<DispatcherType> dispathcherType = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR);
        FilterRegistration.Dynamic f;

        if (null == WebContext.getWebContext().webConfig.HM_SUFFIX || WebContext.getWebContext().webConfig.HM_SUFFIX.trim().isEmpty()) {

            f = sce.getServletContext().addFilter("ZZZZZ", system.web.servlet.LinkServletData.isOpenServlet()
                    ? system.web.filter.JWServletControllerAll.class
                    : system.web.filter.JWControllerAll.class);

            f.addMappingForUrlPatterns(dispathcherType, true, "/*");

            if (WebContext.getWebContext().webConfig.RESOURCE_MANAGER_OPEN) {
                ConfigurationResource cr = new ResourceService().getConfigurationResource(scf.getMyClass());
                sce.getServletContext().setAttribute(ConfigurationResource.class.getName(), cr);
            }
        } else {

            if (!Pattern.compile(".{1}[a-zA-Z0-9_]+", Pattern.CASE_INSENSITIVE).matcher(WebContext.getWebContext().webConfig.HM_SUFFIX).matches()) {
                System.err.println(WebContext.getWebContext().webConfig.HM_SUFFIX + "你在类" + system.web.config.temp.WebConfig.class.getName() + "的实例中，配置属性webConfig.HM_SUFFIX的值未通过.{1}[a-zA-Z0-9_]+的正则表达式的校验。注册框架失败");
                return;
            }
            f = sce.getServletContext().addFilter("ZZZZZ", system.web.servlet.LinkServletData.isOpenServlet()
                    ? system.web.filter.JWServletController.class
                    : system.web.filter.JWController.class);
            f.addMappingForUrlPatterns(dispathcherType, true, "*" + WebContext.getWebContext().webConfig.HM_SUFFIX);
        }

        System.err.println(WebContext.getWebContext().webConfig.HM_SUFFIX + "你在类" + system.web.config.temp.WebConfig.class.getName() + "的实例中，配置属性webConfig.HM_SUFFIX的值通过.{1}[a-zA-Z0-9_]+的正则表达式的校验。注册框架成功");
        sce.getServletContext().setAttribute(WebContext.getWebContext().webConfig.CONTEXTPATH_KEY, WebContext.getWebContext().ContextPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("容器注销了！！！！！！！！wo qu!");
    }
}
