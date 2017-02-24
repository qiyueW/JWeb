package system.web.validate.model;

import system.web.WebContext;

/**
 *
 * @author wangchunzi
 */
public class ValidateJsonModel {

    public static String getJsonKey = "validateJsonKey";
    public static String jsonMustValidate_countIsError_MessageKEY = "errorMessage";
    public static String jsonMustValidate_countIsError_Message="存在必须检验的项。但没有值的存在";
    
    private String jsonKey = getJsonKey;
    private String countIsError_MessageKEY=jsonMustValidate_countIsError_MessageKEY;
    private String countIsError_Message=jsonMustValidate_countIsError_Message;
    private Class jsonClass;
    private String dateformat = WebContext.getWebContext().webConfig.DATE_FORMAT;
    private String timeformat = WebContext.getWebContext().webConfig.TIME_FORMAT;

    public String getCountIsError_Message() {
        return countIsError_Message;
    }

    public void setCountIsError_Message(String countIsError_Message) {
        this.countIsError_Message = countIsError_Message;
    }
    
    
    public String getJsonKey() {
        return jsonKey;
    }

    public String getCountIsError_MessageKEY() {
        return countIsError_MessageKEY;
    }

    public void setCountIsError_MessageKEY(String countIsError_MessageKEY) {
        this.countIsError_MessageKEY = countIsError_MessageKEY;
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
