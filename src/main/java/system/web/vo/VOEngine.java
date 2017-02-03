package system.web.vo;

import java.util.Date;
import system.web.WebContext;
import system.web.vo.model.VO_BeanModel;
import system.web.vo.model.VO_Father;
import system.web.vo.model.VO_OneModel;

/**
 *
 * @author wangchunzi
 */
public class VOEngine extends VO_Father implements VO_OneModel, VO_BeanModel {

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
    public <T> T getObjectByJsonData(Class<T> x, final String requestName) {
        return getObjectByJsonData(request, x, requestName, WebContext.getWebContext().webConfig.DATE_FORMAT, WebContext.getWebContext().webConfig.TIME_FORMAT);
    }
    
    @Override
    public <T> T getObjectByJsonData(Class<T> x, final String requestName, final String dateFormat, final String timeFormat) {
        return getObjectByJsonData(request, x, requestName, dateFormat, timeFormat);
    }
}
