package system.web.validate.model;

/**
 *
 * @author wangchunzi
 */
public class ValidateFieldModel {

    public String name;
    public String regex;
    public boolean isMust;
    public String msg;

    public ValidateFieldModel(String name, String regex, String msg, boolean isMust) {
        this.name = name;
        this.regex = regex;
        this.msg = msg;
        this.isMust = isMust;
    }

    public ValidateFieldModel(String name, String regex, String msg) {
        this.name = name;
        this.regex = regex;
        this.msg = msg;
        this.isMust = true;
    }
}
