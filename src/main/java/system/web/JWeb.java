package system.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import system.web.vo.ReturnType;
import system.web.vo.VOEngine;

/**
 *
 * @author wangchunzi
 */
final public class JWeb extends VOEngine implements ReturnType{

    public final HttpServletRequest request;
    public final HttpServletResponse response;
    public final HttpSession session;
    public final WebContext container = WebContext.getWebContext();

    /**
     * 决定是否走过滤链参数
     */
    private boolean endFilter = false;

    public void forward(final String url) {
        try {
            this.request.getRequestDispatcher(container.ContextPath + url).forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(JWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void forwardServlet(final String url) {
        try {
            this.request.getRequestDispatcher(container.ContextPath + url + container.webConfig.HM_SUFFIX).forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(JWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendRedirect(final String url) {
        try {
            this.response.sendRedirect(container.ContextPath + url);
        } catch (IOException ex) {
            Logger.getLogger(JWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void endFilter() {
        this.endFilter = true;
    }

    /**
     * 判断是否不走过滤链(是：不走过滤链) s
     *
     * @return
     */
    public boolean isEndFilter() {
        return endFilter;
    }

    public JWeb(HttpServletRequest request, HttpServletResponse response) {
        super(request);
        this.request = request;
        this.response = response;
        this.session = this.request.getSession();
    }

    public void printOne(Object obj) {
        try (PrintWriter mo = response.getWriter()) {
            mo.print(obj);
            mo.flush();
            mo.close();
        } catch (IOException ex) {
            Logger.getLogger(JWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
