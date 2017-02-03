package system.web.vo.model;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletRequest;
import system.web.WebContext;
import system.web.vo.VOEngine;

/**
 *
 * @author wangchunzi
 */
public abstract class VO_Father {

    private static final Map<Class, Field[]> map = new HashMap();

    protected static Field[] getBeanModel(Class c) {
        Field[] f = map.get(c);
        return null == f ? iniBeanModel(c) : f;
    }

    private static Field[] iniBeanModel(Class c) {
        Field[] f = c.getDeclaredFields();
        for (Field obj : f) {
            obj.setAccessible(true);
        }
        map.put(c, f);
        return f;
    }

    protected static final <T> T getObjectByJsonData(
            HttpServletRequest request, Class<T> x, final String requestName, final String dateformat, final String timeformat) {

        String str = request.getParameter(requestName);
        if (null == str || str.isEmpty()) {
            return null;
        }
        JsonReader jr = Json.createReader(new StringReader(str));
        JsonObject jobj = jr.readObject();
        jr.close();
        T obj;
        try {
            obj = x.newInstance();
            String json_value;
            for (Field f : VOEngine.getBeanModel(x)) {
                json_value = jobj.getString(f.getName());

                if (null != json_value) {
                    if (f.getType() == String.class) {
                        f.set(obj, json_value);
                        continue;
                    }
                    if (json_value.length() > 0) {

                        if (f.getType() == Integer.class) {
                            f.set(obj, Integer.valueOf(json_value));
                            continue;
                        }
                        if (f.getType() == Double.class) {
                            f.set(obj, Double.valueOf(json_value));
                            continue;
                        }
                        if (f.getType() == Date.class) {
                            try {
                                f.set(obj, dateformat.length() == json_value.length()
                                        ? new SimpleDateFormat(dateformat).parse(json_value)
                                        : new SimpleDateFormat(timeformat).parse(json_value));
                            } catch (ParseException ex) {
                                Logger.getLogger(VOEngine.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            continue;
                        }
                        if (f.getType() == Long.class) {
                            f.set(obj, Long.valueOf(json_value));
                            continue;
                        }
                        if (f.getType() == Short.class) {
                            f.set(obj, Short.valueOf(json_value));
                            continue;
                        }
                        if (f.getType() == Float.class) {
                            f.set(obj, Float.valueOf(json_value));
                            continue;
                        }
                        if (f.getType() == Boolean.class) {
                            f.setBoolean(obj, Boolean.valueOf(json_value));
                        }
                    }
                }
            }
            return obj;
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(VOEngine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(VO_Father.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    protected static final <T> T getObject(HttpServletRequest request, Class<T> x, final String dateformat, final String timeformat) {
        T obj;
        try {
            obj = x.newInstance();
            String request_param;
            for (Field f : VOEngine.getBeanModel(x)) {
                request_param = request.getParameter(f.getName());
                if (null != request_param) {
                    if (f.getType() == String.class) {
                        f.set(obj, request_param);
                        continue;
                    }
                    if (request_param.length() > 0) {

                        if (f.getType() == Integer.class) {
                            f.set(obj, Integer.valueOf(request_param));
                            continue;
                        }
                        if (f.getType() == Double.class) {
                            f.set(obj, Double.valueOf(request_param));
                            continue;
                        }
                        if (f.getType() == Date.class) {
                            try {
                                f.set(obj, dateformat.length() == request_param.length()
                                        ? new SimpleDateFormat(dateformat).parse(request_param)
                                        : new SimpleDateFormat(timeformat).parse(request_param));
                            } catch (ParseException ex) {
                                Logger.getLogger(VOEngine.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            continue;
                        }
                        if (f.getType() == Long.class) {
                            f.set(obj, Long.valueOf(request_param));
                            continue;
                        }
                        if (f.getType() == Short.class) {
                            f.set(obj, Short.valueOf(request_param));
                            continue;
                        }
                        if (f.getType() == Float.class) {
                            f.set(obj, Float.valueOf(request_param));
                            continue;
                        }
                        if (f.getType() == Boolean.class) {
                            f.setBoolean(obj, Boolean.valueOf(request_param));
                        }
                    }
                }
            }
            return obj;
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(VOEngine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(VO_Father.class.getName()).log(Level.SEVERE, null, ex);
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
