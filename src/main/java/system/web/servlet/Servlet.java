package system.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wangchunzi
 */
public abstract class Servlet extends HttpServlet {

    public final static String SERVLET_JWEB_KEY = system.web.JWeb.class.getName();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService((system.web.JWeb) req.getAttribute(SERVLET_JWEB_KEY));
    }

    protected abstract void doService(system.web.JWeb jw)throws ServletException, IOException;
}
