package system.web.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import system.web.JWeb;
import system.web.filter.chain.FilterEngine;
import system.web.validate.ValidateEnging;

/**
 *
 * @author wangchunzi
 */
final public class ServletModelEngine {

    private static final FilterEngine filterEngine = new FilterEngine();
////跳转时，产生的异常

    public static final void doLinkEngine(ServletModel o, JWeb jw, javax.servlet.FilterChain chain)
            throws ServletException, IOException {
        jw.request.setAttribute(Servlet.SERVLET_JWEB_KEY, jw);
        
        try {
            switch (o.instruction) {
                case 0:
                    chain.doFilter(jw.request, jw.response);
                    break;
                case 1:
                    //顶部过滤+HM
                    if (filterEngine.doEngine_error(jw, o.fmTop)) {
                        return;
                    }
                    chain.doFilter(jw.request, jw.response);
                    break;
                case 2://检验+HM
                    if (ValidateEnging.doValidateAndResultError(jw, o.vm)) {
                        return;
                    }
                    chain.doFilter(jw.request, jw.response);
                    break;
                case 4://中部过滤+HM
                    if (filterEngine.doEngine_error(jw, o.fmCenter)) {
                        return;
                    }
                    chain.doFilter(jw.request, jw.response);
                    break;

                case 8://HM+尾部强制过滤
                    chain.doFilter(jw.request, jw.response);
                    filterEngine.doEngine_error(jw, o.fmButtom);
                    break;

                case 3://顶部过滤+校验+HM
                    if (filterEngine.doEngine_error(jw, o.fmTop) || ValidateEnging.doValidateAndResultError(jw, o.vm)) {
                        return;
                    }
                    chain.doFilter(jw.request, jw.response);
                    break;
                case 5://顶部+中部过滤+HM
                    if (filterEngine.doEngine_error(jw, o.fmTop) || filterEngine.doEngine_error(jw, o.fmCenter)) {
                        return;
                    }
                    chain.doFilter(jw.request, jw.response);
                    break;
                case 9:
                    if (filterEngine.doEngine_error(jw, o.fmTop)) {
                        return;
                    }
                    chain.doFilter(jw.request, jw.response);
                    filterEngine.doEngine_error(jw, o.fmButtom);
                    break;
                case 7:
                    if (filterEngine.doEngine_error(jw, o.fmTop)//顶部过滤
                            || ValidateEnging.doValidateAndResultError(jw, o.vm) || filterEngine.doEngine_error(jw, o.fmCenter)//中部过滤
                            ) {
                        return;
                    }
                    chain.doFilter(jw.request, jw.response);
                    break;
                case 11:
                    if (filterEngine.doEngine_error(jw, o.fmTop)//顶部过滤
                            || ValidateEnging.doValidateAndResultError(jw, o.vm) //|| filterEngine.doEngine_error(jw, o.fmCenter)//中部过滤
                            ) {
                        return;
                    }
                    chain.doFilter(jw.request, jw.response);
                    filterEngine.doEngine_error(jw, o.fmButtom);
                    break;
                case 13:
                    if (filterEngine.doEngine_error(jw, o.fmTop)//顶部过滤
                            //                                || ValidateEnging.doValidateAndResultError(jw, o.vm, o) 
                            || filterEngine.doEngine_error(jw, o.fmCenter)//中部过滤
                            ) {
                        return;
                    }
                    chain.doFilter(jw.request, jw.response);
                    filterEngine.doEngine_error(jw, o.fmButtom);
                    break;
                case 15:
                    if (filterEngine.doEngine_error(jw, o.fmTop)//顶部过滤
                            || ValidateEnging.doValidateAndResultError(jw, o.vm) || filterEngine.doEngine_error(jw, o.fmCenter)//中部过滤
                            ) {
                        return;
                    }
                    chain.doFilter(jw.request, jw.response);
                    filterEngine.doEngine_error(jw, o.fmButtom);
                    break;
                case 6:
                    if (ValidateEnging.doValidateAndResultError(jw, o.vm) || filterEngine.doEngine_error(jw, o.fmCenter)//中部过滤
                            ) {
                        return;
                    }
                    chain.doFilter(jw.request, jw.response);
                    break;
                case 10://校验+HM+尾部过滤
                    if (ValidateEnging.doValidateAndResultError(jw, o.vm)) {//校验
                        return;
                    }
                    chain.doFilter(jw.request, jw.response);
                    filterEngine.doEngine_error(jw, o.fmButtom);
                    break;

                case 14:
                    if (ValidateEnging.doValidateAndResultError(jw, o.vm) || filterEngine.doEngine_error(jw, o.fmCenter)//中部过滤
                            ) {
                        return;
                    }
                    chain.doFilter(jw.request, jw.response);
                    filterEngine.doEngine_error(jw, o.fmButtom);
                    break;
                case 12:
                    if (filterEngine.doEngine_error(jw, o.fmCenter)//中部过滤
                            ) {
                        return;
                    }
                    chain.doFilter(jw.request, jw.response);
                    filterEngine.doEngine_error(jw, o.fmButtom);
                    break;
            }

        } catch (IllegalArgumentException | SecurityException ex) {
            Logger.getLogger(ServletModelEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
