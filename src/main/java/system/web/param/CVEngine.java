package system.web.param;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import system.base.date.DateService;
import system.web.JWeb;
import system.web.param.model.VO_BeanModel;
import system.web.param.model.VO_Father;
import system.web.param.model.VO_OneModel;
import system.web.param.model.VO_Tool;

/**
 *
 * @author wangchunzi
 */
public class CVEngine extends VO_Father implements VO_OneModel, VO_BeanModel, VO_Tool {

    private static final CVEngine jweb_cv = new CVEngine();

    private CVEngine() {

    }

    public static CVEngine getCVEngine() {
        return jweb_cv;
    }

    @Override
    public String getStringValue(JWeb jw, String paramName) {
        String value = jw.request.getParameter(paramName);
        return null == value ? null : value;
    }

    @Override
    public String getStringValue(JWeb jw, String paramName, String regex) {
        String value = jw.request.getParameter(paramName);
        return null == value
                ? null
                : Pattern.compile(regex).matcher(value).matches()
                ? value
                : null;
    }

    @Override
    public Integer getIntegerValue(JWeb jw, String paramName) {
        String value = jw.request.getParameter(paramName);
        return null == value ? null : Integer.valueOf(value);
    }

    @Override
    public Integer getIntegerValue(JWeb jw, String paramName, String regex) {
        String value = jw.request.getParameter(paramName);
        return null == value
                ? null
                : Pattern.compile(regex).matcher(value).matches()
                ? Integer.valueOf(value)
                : null;
    }

    @Override
    public Date getDateValue(JWeb jw, String paramName, String dateFomat) {
        try {
            String value = jw.request.getParameter(paramName);
            return null == value ? null : new SimpleDateFormat(dateFomat).parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(CVEngine.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Date getDateValue(JWeb jw, String paramName, String regex, String dateFomat) {
        try {
            String value = jw.request.getParameter(paramName);
            return null == value
                    ? null
                    : Pattern.compile(regex).matcher(value).matches()
                    ? new SimpleDateFormat(dateFomat).parse(value)
                    : null;
        } catch (ParseException ex) {
            Logger.getLogger(CVEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Float getFloatValue(JWeb jw, String paramName) {
        String value = jw.request.getParameter(paramName);
        return null == value ? null : Float.valueOf(value);
    }

    @Override
    public Float getFloatValue(JWeb jw, String paramName, String regex) {
        String value = jw.request.getParameter(paramName);
        return null == value
                ? null
                : Pattern.compile(regex).matcher(value).matches()
                ? Float.valueOf(value)
                : null;
    }

    @Override
    public Double getDoubleValue(JWeb jw, String paramName) {
        String value = jw.request.getParameter(paramName);
        return null == value ? null : Double.valueOf(value);
    }

    @Override
    public Double getDoubleValue(JWeb jw, String paramName, String regex) {
        String value = jw.request.getParameter(paramName);
        return null == value
                ? null
                : Pattern.compile(regex).matcher(value).matches()
                ? Double.valueOf(value)
                : null;
    }

    @Override
    public Long getLongValue(JWeb jw, String paramName) {
        String value = jw.request.getParameter(paramName);
        return null == value ? null : Long.valueOf(value);
    }

    @Override
    public Long getLongValue(JWeb jw, String paramName, String regex) {
        String value = jw.request.getParameter(paramName);
        return null == value
                ? null
                : Pattern.compile(regex).matcher(value).matches()
                ? Long.valueOf(value)
                : null;
    }

    @Override
    public Short getShortValue(JWeb jw, String paramName) {
        String value = jw.request.getParameter(paramName);
        return null == value ? null : Short.valueOf(value);
    }

    @Override
    public Short getShortValue(JWeb jw, String paramName, String regex) {
        String value = jw.request.getParameter(paramName);
        return null == value
                ? null
                : Pattern.compile(regex).matcher(value).matches()
                ? Short.valueOf(value)
                : null;
    }

    @Override
    public Boolean getBooleanValue(JWeb jw, String paramName) {
        String value = jw.request.getParameter(paramName);
        return null == value ? null : Boolean.valueOf(value);
    }

    @Override
    public Boolean getBooleanValue(JWeb jw, String paramName, String regex) {
        String value = jw.request.getParameter(paramName);
        return null == value
                ? null
                : Pattern.compile(regex).matcher(value).matches()
                ? Boolean.valueOf(value)
                : null;
    }

    @Override
    public Byte getByteValue(JWeb jw, String paramName) {
        String value = jw.request.getParameter(paramName);
        return null == value ? null : Byte.parseByte(value);
    }

    @Override
    public Byte getByteValue(JWeb jw, String paramName, String regex) {
        String value = jw.request.getParameter(paramName);
        return null == value
                ? null
                : Pattern.compile(regex).matcher(value).matches()
                ? Byte.parseByte(value)
                : null;
    }

    @Override
    public <T> T getObject(Class<T> x, JWeb jw) {
        T obj;
        try {
            obj = x.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CVEngine.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        String request_param;
        for (Field f : CVEngine.getBeanModel(x)) {
            request_param = jw.request.getParameter(f.getName());

            try {
                if (null != request_param || request_param.length() > 0) {
                    if (f.getType() == String.class) {
                        f.set(obj, request_param);
                    } else if (f.getType() == Integer.class) {
                        f.set(obj, Integer.valueOf(request_param));
                    } else if (f.getType() == Double.class) {
                        f.set(obj, Double.valueOf(request_param));
                    } else if (f.getType() == Short.class) {
                        f.setShort(obj, Short.valueOf(request_param));
                    } else if (f.getType() == Float.class) {
                        f.setFloat(obj, Float.valueOf(request_param));
                    } else if (f.getType() == Boolean.class) {
                        f.setBoolean(obj, Boolean.valueOf(request_param));
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(CVEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return obj;
    }

    @Override
    public <T> T getObject(Class<T> x, JWeb jw, String[] dateFields, String dateFormat, String[] timeFields, String timeFormat) {
        String request_param;
        boolean fieldIsTime;
        T obj;
        try {
            obj = x.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CVEngine.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        for (Field f : CVEngine.getBeanModel(x)) {
            request_param = jw.request.getParameter(f.getName());
            try {
                if (null != request_param) {//     || request_param.length() > 0
                    if (f.getType() == String.class) {
                        f.set(obj, request_param);

                    } else if (request_param.length() > 0) {
                        if (f.getType() == Integer.class) {

                            f.set(obj, Integer.valueOf(request_param));

                        } else if (f.getType() == Double.class) {

                            f.set(obj, Double.valueOf(request_param));

                        } else if (f.getType() == Date.class) {
                            fieldIsTime = true;
                            System.out.println("Date request_param:" + f.getName() + "////" + request_param);
                            for (String myfield : dateFields) {
                                if (myfield.equalsIgnoreCase(f.getName())) {
                                    //new SimpleDateFormat(dateFomat).parse(value)
                                    f.set(obj, new SimpleDateFormat(dateFormat).parse(request_param));
                                    fieldIsTime = false;
                                }
                            }
                            if (fieldIsTime) {
                                f.set(obj, new SimpleDateFormat(timeFormat).parse(request_param));
                            }

                        } else if (f.getType() == Short.class) {
                            f.setShort(obj, Short.valueOf(request_param));

                        } else if (f.getType() == Float.class) {
                            f.setFloat(obj, Float.valueOf(request_param));

                        } else if (f.getType() == Boolean.class) {
                            f.setBoolean(obj, Boolean.valueOf(request_param));
                        }
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException | ParseException ex) {
                Logger.getLogger(CVEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return obj;
    }

    @Override
    public <T> T getObjectByJsonData(Class<T> x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 默认取 reqParam1参与reqParam2参 的值。然后组成时间范围的sql条件语句。
     * @param jw JWeb对象
     * @param qz 在返回字符串前 添加的字符串
     * @param sqlField 数据库表中某一字段。
     * @param defaultValue 默认返回，当取不到用户的日期传参时。
     * @param reqParam1
     * @param reqParam2
     * @return 
     */
    @Override
    public String getDate(JWeb jw, String qz, String sqlField, String defaultValue, String reqParam1, String reqParam2) {
        String d1 = jw.getString(reqParam1);
        String d2 = jw.getString(reqParam2);
        return tDateToSQL(jw,qz,sqlField,defaultValue,d1,d2);
    }

    /**
     * 默认取 paramkeyDate1参与paramkeyDate2参 的值。然后组成时间范围的sql条件语句。
     * @param jw JWeb对象
     * @param qz 在返回字符串前 添加的字符串
     * @param sqlField 数据库表中某一字段。
     * @return 
     */
    @Override
    public String getDate(JWeb jw, String qz, String sqlField) {
        String d1 = jw.getString("paramkeyDate1");
        String d2 = jw.getString("paramkeyDate2");
        return tDateToSQL(jw,qz,sqlField,null,d1,d2);
    }
 
    private static String tDateToSQL(JWeb jw, String qz, String sqlField, String defaultValue, String d1, String d2) {
        Date date1 = null, date2 = null;
        boolean b1 = false, b2 = false;
        if (!(null == d1 || d1.isEmpty())) {
            date1 = DateService.TO.toDate(d1);
            b1 = true;
        }

        if (!(null == d2 || d2.isEmpty())) {
            date2 = DateService.TO.toDate(d2);
            b2 = true;
        }

        if (null == date1 && null == date2) {
            return null == defaultValue ? "" : " "+qz+" "+defaultValue;
        }
        if (b1 && b2) {
            return date1.getTime()<date2.getTime()?
                    " "+qz+" "+sqlField + " BETWEEN '" + d1 + "' AND '" + d2+"'"
                    :" "+qz+" "+sqlField + " BETWEEN '" + d2 + "' AND '" + d1+"'";
        }
        if(b1){
            return " "+qz+" "+sqlField +">='"+b1+"'";
        }
        return " "+qz+" "+sqlField +"<='"+b2+"'";
    }
}
