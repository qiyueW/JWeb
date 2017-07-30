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
import system.web.hm.HMModelEngine;
import system.web.servlet.LinkServletData;
import system.web.servlet.ServletModel;
import system.web.servlet.ServletModelEngine;

/**
 * 使用固定后缀的。采用此配置。
 *
 * @author wangchunzi
 */
public class JWServletController implements Filter {

    private FilterConfig filterConfig = null;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        req.setCharacterEncoding(WebContext.getWebContext().webConfig.ENCODE);
        resp.setCharacterEncoding(WebContext.getWebContext().webConfig.ENCODE);
        JWeb jw = new JWeb(req, resp);
        String x = req.getServletPath();
        if (system.web.power.PowerCheckMain.checkPowerIsError(jw, x)) {
            return;
        }
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
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
