package system.web.validate.model;

import java.util.HashMap;
import java.util.Map;
import system.web.JWeb;
import system.web.WebContext;

/**
 *
 * @author wangchunzi
 */
public abstract class ValidateModel {

    private final Map<String, ValidateFieldModel> map = new HashMap();
    public final static String VALIDATE_MSG_KEY = "validate_msg";
    public String url = "";
    public String msg_key = "";

    public void setReturnMsgKey(String key) {
        this.msg_key = (null == key || key.isEmpty()) ? "" : key;
    }

    public void setReturnURL(String url) {
        url = (null == url || url.isEmpty()) ? "" : url.trim();
        if (url.isEmpty()) {
            this.url = "";
        } else {
            this.url = WebContext.getWebContext().ContextPath + url
                    + (url.contains(".") ? "" : WebContext.getWebContext().webConfig.HM_SUFFIX);
        }
    }

    public Map<String, ValidateFieldModel> getValidateFieldModel() {
        return this.map;
    }

    public abstract void iniValidate();

    public ValidateModel put(String name, String regex, String msg, boolean ismust) {
        map.put(name, new ValidateFieldModel(name, regex, msg, ismust));
        return this;
    }

    /**
     * 自定义复验
     *
     * @param jw
     * @param map
     * @param vr
     * @return
     */
    public abstract ValidateResultModel recheck(JWeb jw, Map<String, ValidateFieldModel> map, ValidateResultModel vr);

    /**
     * 校验失败
     *
     * @param jw
     * @param vr
     */
    public void error(JWeb jw, ValidateResultModel vr) {
        if (url.isEmpty()) {
            jw.printOne(vr.getMsgByJson());
//            System.out.println(vr.getMsgByJson());/
            return;
        }
        jw.request.setAttribute(this.msg_key.isEmpty() ? ValidateModel.VALIDATE_MSG_KEY : this.msg_key, vr.getMsgByMap());
        jw.forward(url);
    }

    /**
     * 复验失败
     *
     * @param jw
     * @param vr
     */
    public void errorRecheck(JWeb jw, ValidateResultModel vr) {
        if (url.isEmpty()) {
            jw.printOne(vr.getMsgByJson());
            return;
        }
        jw.request.setAttribute(this.msg_key.isEmpty() ? ValidateModel.VALIDATE_MSG_KEY : this.msg_key, vr.getMsgByMap());
        jw.forward(url);
    }

    /**
     * 成功
     *
     * @param jw
     * @param vr
     */
    public void success(JWeb jw, ValidateResultModel vr) {

    }
}
