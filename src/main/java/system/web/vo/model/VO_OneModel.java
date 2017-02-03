package system.web.vo.model;

import java.util.Date;

/**
 *
 * @author wangchunzi
 */
public interface VO_OneModel {

    public String   getString(final String paramName);
    public Short    getShort(final String paramName);
    public int      getInt(final String paramName);
    public Integer  getInteger(final String paramName);
    public Long     getLong(final String paramName);
    public Float    getFloat(final String paramName);
    public Double   getDouble(final String paramName);
    public Boolean  getBoolean(final String paramName);
    public Date     getDate(final String paramName);
    public Date     getDate(final String paramName, String dateFomat);
    
    public String   getString(final String paramName,final String defaultValue);
    public Short    getShort(final String paramName,final Short defaultValue);
    public int      getInt(final String paramName,final int defaultValue);
    public Integer  getInteger(final String paramName,final Integer defaultValue);
    public Long     getLong(final String paramName,final Long defaultValue);
    public Float    getFloat(final String paramName,final Float defaultValue);
    public Double   getDouble(final String paramName,final Double defaultValue);
    public Boolean  getBoolean(final String paramName,final Boolean defaultValue);
    public Date     getDate(final String paramName,final Date defaultValue);
    public Date     getDate(final String paramName, final String dateFomat,final Date defaultValue);
    
}
