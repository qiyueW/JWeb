package system.web.validate.model;

import system.web.WebContext;

/**
 *
 * @author wangchunzi
 */
public class ValidateJsonModel {

    public static final String KEY = "validateJsonKey";
    private String jsonKey = KEY;
    private Class jsonClass;
    private String dateformat = WebContext.getWebContext().webConfig.DATE_FORMAT;
    private String timeformat = WebContext.getWebContext().webConfig.TIME_FORMAT;
    
    public String getJsonKey() {
        return jsonKey;
    }

    public void setJsonKey(String jsonKey) {
        this.jsonKey = jsonKey;
    }

    public Class getJsonClass() {
        return jsonClass;
    }

    public void setJsonClass(Class jsonClass) {
        this.jsonClass = jsonClass;
    }

    public String getDateformat() {
        return dateformat;
    }

    public void setDateformat(String dateformat) {
        this.dateformat = dateformat;
    }

    public String getTimeformat() {
        return timeformat;
    }

    public void setTimeformat(String timeformat) {
        this.timeformat = timeformat;
    }

}
