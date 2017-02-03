package system.web.param.model;

import java.util.Date;
import system.web.JWeb;

/**
 *
 * @author wangchunzi
 */
public interface VO_OneModel {

    /**
     *
     * @param jw
     * @param paramName
     * @return
     */
    public String getStringValue(JWeb jw, String paramName);

    public String getStringValue(JWeb jw, String paramName, String regex);

    public Integer getIntegerValue(JWeb jw, String paramName);

    public Integer getIntegerValue(JWeb jw, String paramName, String regex);

    public Date getDateValue(JWeb jw, String paramName, String dateFomat);

    public Date getDateValue(JWeb jw, String paramName, String regex, String dateFomat);

    public Float getFloatValue(JWeb jw, String paramName);

    public Float getFloatValue(JWeb jw, String paramName, String regex);

    public Double getDoubleValue(JWeb jw, String paramName);

    public Double getDoubleValue(JWeb jw, String paramName, String regex);

    public Long getLongValue(JWeb jw, String paramName);

    public Long getLongValue(JWeb jw, String paramName, String regex);

    public Short getShortValue(JWeb jw, String paramName);

    public Short getShortValue(JWeb jw, String paramName, String regex);

    public Boolean getBooleanValue(JWeb jw, String paramName);

    public Boolean getBooleanValue(JWeb jw, String paramName, String regex);

    public Byte getByteValue(JWeb jw, String paramName);

    public Byte getByteValue(JWeb jw, String paramName, String regex);
}
