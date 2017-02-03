package system.web.hm;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import system.web.JWeb;
import system.web.filter.chain.FilterEngine;
import system.web.hm.model.LinkHMData;
import system.web.hm.model.LinkHMModel;
import system.web.validate.ValidateEnging;

/**
 *
 * @author wangchunzi
 */
final public class HMModelEngine {

    private static final FilterEngine filterEngine = new FilterEngine();
////跳转时，产生的异常
    public static final void doLinkEngine(final String url, JWeb jw)
            throws ServletException, IOException {
        try {
            LinkHMModel o = LinkHMData.getLinkModel(url);
            if (null == o) {
                return;
            }
            switch (o.instruction) {
                case 0:
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    return;
                case 1:
                    //顶部过滤+HM
                    if (filterEngine.doEngine_error(jw, o.fmTop)) {
                        return;
                    }
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    return;
                case 2://检验+HM
                    if (ValidateEnging.doValidateAndResultError(jw, o.vm)) {
                        return;
                    }
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    return;
                case 4://中部过滤+HM
                    if (filterEngine.doEngine_error(jw, o.fmCenter)) {
                        return;
                    }
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    return;

                case 8://HM+尾部强制过滤
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    filterEngine.doEngine_error(jw, o.fmButtom);
                    return;

                case 3://顶部过滤+校验+HM
                    if (filterEngine.doEngine_error(jw, o.fmTop) || ValidateEnging.doValidateAndResultError(jw, o.vm)) {
                        return;
                    }
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    return;
                case 5://顶部+中部过滤+HM
                    if (filterEngine.doEngine_error(jw, o.fmTop) || filterEngine.doEngine_error(jw, o.fmCenter)) {
                        return;
                    }
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    return;
                case 9:
                    if (filterEngine.doEngine_error(jw, o.fmTop)) {
                        return;
                    }
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    filterEngine.doEngine_error(jw, o.fmButtom);
                    return;
                case 7:
                    if (filterEngine.doEngine_error(jw, o.fmTop)//顶部过滤
                            || ValidateEnging.doValidateAndResultError(jw, o.vm) || filterEngine.doEngine_error(jw, o.fmCenter)//中部过滤
                            ) {
                        return;
                    }
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    return;
                case 11:
                    if (filterEngine.doEngine_error(jw, o.fmTop)//顶部过滤
                            || ValidateEnging.doValidateAndResultError(jw, o.vm) //|| filterEngine.doEngine_error(jw, o.fmCenter)//中部过滤
                            ) {
                        return;
                    }
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    filterEngine.doEngine_error(jw, o.fmButtom);
                    return;
                case 13:
                    if (filterEngine.doEngine_error(jw, o.fmTop)//顶部过滤
                            || filterEngine.doEngine_error(jw, o.fmCenter)//中部过滤
                            ) {
                        return;
                    }
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    filterEngine.doEngine_error(jw, o.fmButtom);
                    return;
                case 15:
                    if (filterEngine.doEngine_error(jw, o.fmTop)//顶部过滤
                            || ValidateEnging.doValidateAndResultError(jw, o.vm) || filterEngine.doEngine_error(jw, o.fmCenter)//中部过滤
                            ) {
                        return;
                    }
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    filterEngine.doEngine_error(jw, o.fmButtom);
                    return;
                case 6:
                    if (ValidateEnging.doValidateAndResultError(jw, o.vm) || filterEngine.doEngine_error(jw, o.fmCenter)//中部过滤
                            ) {
                        return;
                    }
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    return;
                case 10://校验+HM+尾部过滤
                    if (ValidateEnging.doValidateAndResultError(jw, o.vm)) {//校验
                        return;
                    }
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    filterEngine.doEngine_error(jw, o.fmButtom);
                    return;
                case 14:
                    if (ValidateEnging.doValidateAndResultError(jw, o.vm) || filterEngine.doEngine_error(jw, o.fmCenter)//中部过滤
                            ) {
                        return;
                    }
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    filterEngine.doEngine_error(jw, o.fmButtom);
                    return;
                case 12:
                    if (filterEngine.doEngine_error(jw, o.fmCenter)//中部过滤
                            ) {
                        return;
                    }
                    if (o.isStaticMethod) {
                        o.method.invoke(null, jw);
                        return;
                    }
                    o.method.invoke(o.hClass.getConstructor(JWeb.class).newInstance(jw));
                    filterEngine.doEngine_error(jw, o.fmButtom);
            }

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException ex) {
            Logger.getLogger(HMModelEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
