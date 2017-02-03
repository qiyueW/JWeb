package system.web.filter;

import system.web.JWeb;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import system.web.WebContext;
import system.web.filter.resource.ConfigurationResource;
import system.web.hm.HMModelEngine;
import system.web.servlet.LinkServletData;
import system.web.servlet.ServletModel;
import system.web.servlet.ServletModelEngine;

/**
 * 过滤全部的请求的，采用此配置。
 *
 * @author wangchunzi
 */
public class JWServletControllerAll implements Filter {

    private FilterConfig filterConfig = null;
    private ConfigurationResource cr;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        req.setCharacterEncoding(WebContext.getWebContext().webConfig.ENCODE);
        resp.setCharacterEncoding(WebContext.getWebContext().webConfig.ENCODE);
        String x = req.getServletPath();
        
        if (WebContext.getWebContext().webConfig.RESOURCE_MANAGER_OPEN && cr.isStaticResource(x, req, resp)) {
            chain.doFilter(request, response);
            return;
        }

        JWeb jw = new JWeb(req, resp);
        ServletModel linkModel = LinkServletData.getLinkModel(x);

        if (null == linkModel) {
            try {
                HMModelEngine.doLinkEngine(x, jw);
            } catch (ServletException | IOException ex) {

            }
            return;
        }
        ServletModelEngine.doLinkEngine(linkModel, jw, chain);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println(this.getClass().getName() + "注册成功！");
        this.filterConfig = filterConfig;

        if (WebContext.getWebContext().webConfig.RESOURCE_MANAGER_OPEN) {
            cr = (ConfigurationResource) filterConfig.getServletContext().getAttribute(ConfigurationResource.class.getName());
            filterConfig.getServletContext().removeAttribute(ConfigurationResource.class.getName());
        }
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
