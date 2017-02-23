package system.web.vo.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import system.base.jclass.ClassFactory;
import system.base.jclass.ClassInfo;
import system.base.jclass.field.FieldInfo;
import system.web.WebContext;
import system.web.json.HTTPJSONFactory;
import system.web.json.JsonEngineModel;
import system.web.vo.VOEngine;

/**
 *
 * @author wangchunzi
 */
public abstract class VO_Father {

    protected static final JsonEngineModel JEM = HTTPJSONFactory.getJsonModel();

    final protected static <T> List<T> getListBySimpleJsonData(HttpServletRequest request, Class<T> x, final String requestName, final String dateformat, final String timeformat) {
        String str = request.getParameter(requestName);
        if (null == str || str.isEmpty()) {
            return null;
        }
        return JEM.getListBySimpleJsonData(x, str, dateformat, timeformat);
    }

    final protected static <T> T getObjectBySimpleJsonData(HttpServletRequest request, Class<T> x, final String requestName, final String dateformat, final String timeformat) {
        String str = request.getParameter(requestName);
        if (null == str || str.isEmpty()) {
            return null;
        }
        return JEM.getObjectBySimpleJsonData(x, str, dateformat, timeformat);
    }

    final protected static <T> List<T> f_getListBySimpleJsonData__CI_TIME(HttpServletRequest request, Class<T> x, final String requestName) {
        String str = request.getParameter(requestName);
        if (null == str || str.isEmpty()) {
            return null;
        }
        return JEM.getListBySimpleJsonData_CI_TIME(x, str);
    }

    final protected static <T> T f_getObjectBySimpleJsonData_CI_TIME(HttpServletRequest request, Class<T> x, final String requestName) {
        String str = request.getParameter(requestName);
        if (null == str || str.isEmpty()) {
            return null;
        }
        return JEM.getObjectBySimpleJsonData_CI_TIME(x, str);
    }

    protected static final <T> T getObject(HttpServletRequest request, Class<T> x, final String dateformat, final String timeformat) {
        T obj;
        ClassInfo ci = ClassFactory.get(x);
        try {
            obj = x.newInstance();
            for (FieldInfo fi : ci.fieldInfo) {
                fi.setValue(obj, fi.get(request.getParameter(fi.fiel_name), dateformat, timeformat));
            }
            return obj;
        } catch (IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
        }
        return null;
    }

    protected static final Date getDate(HttpServletRequest request, final String paramName, Date defaultValue) {
        try {
            String value = request.getParameter(paramName);
            return null == value
                    ? defaultValue
                    : (WebContext.getWebContext().webConfig.DATE_FORMAT.length() == value.length()
                    ? new SimpleDateFormat(WebContext.getWebContext().webConfig.DATE_FORMAT).parse(value)
                    : new SimpleDateFormat(WebContext.getWebContext().webConfig.TIME_FORMAT).parse(value));
        } catch (ParseException ex) {
            Logger.getLogger(VOEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    protected static final Date getDate(HttpServletRequest request, final String paramName, final String dateFomat, Date defaultValue) {
        try {
            String value = request.getParameter(paramName);
            return null == value ? defaultValue : new SimpleDateFormat(dateFomat).parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(VOEngine.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
