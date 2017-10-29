package system.web.vo;

import java.util.Date;
import java.util.List;
import system.base.date.DateService;
import system.web.JWeb;
import system.web.WebContext;
import system.web.vo.model.VO_Tool;
import system.web.vo.model.VO_BeanModel;
import system.web.vo.model.VO_Father;
import system.web.vo.model.VO_OneModel;

/**
 *
 * @author wangchunzi
 */
public class VOEngine extends VO_Father implements VO_OneModel, VO_BeanModel,VO_Tool {

    javax.servlet.http.HttpServletRequest request;

    public VOEngine(javax.servlet.http.HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String getString(final String paramName) {
        String value = request.getParameter(paramName);
        return null == value ? null : value;
    }

    @Override
    public String getString(final String paramName, final String defaultValue) {
        String value = request.getParameter(paramName);
        return null == value ? defaultValue : value;
    }

    @Override
    public Short getShort(final String paramName) {
        String value = request.getParameter(paramName);
        return null == value ? null : Short.valueOf(value);
    }

    @Override
    public Short getShort(final String paramName, final Short defaultValue) {
        String value = request.getParameter(paramName);
        return null == value ? defaultValue : Short.valueOf(value);
    }

    @Override
    public int getInt(final String paramName) {
        return Integer.parseInt(request.getParameter(paramName));
    }

    @Override
    public int getInt(final String paramName, final int defaultValue) {
        String value = request.getParameter(paramName);
        return null == value ? defaultValue : Integer.parseInt(value);
    }

    @Override
    public Integer getInteger(final String paramName) {
        String value = request.getParameter(paramName);
        return null == value ? null : Integer.valueOf(value);
    }

    @Override
    public Integer getInteger(final String paramName, final Integer defaultValue) {
        String value = request.getParameter(paramName);
        return null == value ? defaultValue : Integer.valueOf(value);
    }

    @Override
    public Long getLong(final String paramName) {
        String value = request.getParameter(paramName);
        return null == value ? null : Long.valueOf(value);
    }

    @Override
    public Long getLong(final String paramName, final Long defaultValue) {
        String value = request.getParameter(paramName);
        return null == value ? defaultValue : Long.valueOf(value);
    }

    @Override
    public Float getFloat(final String paramName) {
        String value = request.getParameter(paramName);
        return null == value ? null : Float.valueOf(value);
    }

    @Override
    public Float getFloat(final String paramName, final Float defaultValue) {
        String value = request.getParameter(paramName);
        return null == value ? defaultValue : Float.valueOf(value);
    }

    @Override
    public Double getDouble(final String paramName) {
        String value = request.getParameter(paramName);
        return null == value ? null : Double.valueOf(value);
    }

    @Override
    public Double getDouble(final String paramName, final Double defaultValue) {
        String value = request.getParameter(paramName);
        return null == value ? defaultValue : Double.valueOf(value);
    }

    @Override
    public Boolean getBoolean(final String paramName) {
        String value = request.getParameter(paramName);
        return null == value ? null : Boolean.valueOf(value);
    }

    @Override
    public Boolean getBoolean(final String paramName, final Boolean defaultValue) {
        String value = request.getParameter(paramName);
        return null == value ? defaultValue : Boolean.valueOf(value);
    }

    @Override
    public Date getDate(final String paramName) {
        return getDate(request, paramName, null);
    }

    @Override
    public Date getDate(final String paramName, final Date defaultValue) {
        return getDate(request, paramName, defaultValue);
    }

    @Override
    public Date getDate(final String paramName, final String dateFomat) {
        return getDate(request, paramName, dateFomat, null);
    }

    @Override
    public Date getDate(final String paramName, final String dateFomat, final Date defaultValue) {
        return getDate(request, paramName, dateFomat, defaultValue);
    }

    @Override
    public <T> T getObject(Class<T> x) {
        return getObject(request, x, WebContext.getWebContext().webConfig.DATE_FORMAT, WebContext.getWebContext().webConfig.TIME_FORMAT);
    }

    @Override
    public <T> T getObject(Class<T> x, final String dateFormat, final String timeFormat) {
        return getObject(request, x, dateFormat, timeFormat);
    }

    @Override
    public <T> List<T> getListBySimpleJsonData(Class<T> x, String requestName) {
        return getListBySimpleJsonData(request, x, requestName, WebContext.getWebContext().webConfig.DATE_FORMAT, WebContext.getWebContext().webConfig.TIME_FORMAT);
    }

    @Override
    public <T> List<T> getListBySimpleJsonData(Class<T> x, String requestName, String dateformat, String timeformat) {
        return getListBySimpleJsonData(request, x, requestName, dateformat, timeformat);
    }

    @Override
    public <T> T getObjectBySimpleJsonData(Class<T> x, String requestName) {
        return getObjectBySimpleJsonData(request, x, requestName, WebContext.getWebContext().webConfig.DATE_FORMAT, WebContext.getWebContext().webConfig.TIME_FORMAT);
    }

    @Override
    public <T> T getObjectBySimpleJsonData(Class<T> x, String requestName, String dateformat, String timeformat) {
        return getObjectBySimpleJsonData(request, x, requestName, dateformat, timeformat);
    }

    @Override
    public <T> T getObjectBySimpleJsonData_CI_TIME(Class<T> x, String requestName) {
        return f_getObjectBySimpleJsonData_CI_TIME(request, x, requestName);
    }

    @Override
    public <T> List<T> getListBySimpleJsonData_CI_TIME(Class<T> x, String requestName) {
        return f_getListBySimpleJsonData__CI_TIME(request, x, requestName);
    }

    /**
     * 默认取 reqParam1参与reqParam2参 的值。然后组成时间范围的sql条件语句。
     *
     * @param qz 在返回字符串前 添加的字符串
     * @param sqlField 数据库表中某一字段。
     * @param defaultValue 默认返回，当取不到用户的日期传参时。
     * @param reqParam1
     * @param reqParam2
     * @return
     */
    @Override
    public String getDateScope(String qz, String sqlField, String defaultValue, String reqParam1, String reqParam2) {
        String d1 =  request.getParameter(reqParam1);
        String d2 =  request.getParameter(reqParam2);
        return tDateToSQL(qz, sqlField, defaultValue, d1, d2);
    }

    /**
     * 默认取 paramkeyDate1参与paramkeyDate2参 的值。然后组成时间范围的sql条件语句。
     *
     * @param qz 在返回字符串前 添加的字符串
     * @param sqlField 数据库表中某一字段。
     * @return
     */
    @Override
    public String getDateScope(String qz, String sqlField) {
        String d1 = request.getParameter("paramkeyDate1");
        String d2 = request.getParameter("paramkeyDate2");
        return tDateToSQL(qz, sqlField, null, d1, d2);
    }

    private static String tDateToSQL(String qz, String sqlField, String defaultValue, String d1, String d2) {
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
            return null == defaultValue ? "" : " " + qz + " " + defaultValue;
        }
        if (b1 && b2) {
            return date1.getTime() < date2.getTime()
                    ? " " + qz + " " + sqlField + " BETWEEN '" + d1 + "' AND '" + d2 + "'"
                    : " " + qz + " " + sqlField + " BETWEEN '" + d2 + "' AND '" + d1 + "'";
        }
        if (b1) {
            return " " + qz + " " + sqlField + ">='" + d1 + "'";
        }
        return " " + qz + " " + sqlField + "<='" + d2 + "'";
    }

}
