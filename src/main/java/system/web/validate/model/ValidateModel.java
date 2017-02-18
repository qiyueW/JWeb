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
    /**
     * {"statusCode":"99","msg":#}
     */
    final public static String JSON_MODEL="{\"statusCode\":\"99\",\"msg\":#}";
    private final Map<String, ValidateFieldModel> map = new HashMap();
    public final static String VALIDATE_MSG_KEY = "validate_msg";
    public final String url;
    public final String msg_key;
    public final String jsonModel;
    public final boolean returnJSON;

    /**
     * 请将#代表错误信息。例如:{"code":"1","msg":#},如此，系统会自动将#替换成检验不通过的信息。
     *
     * @param jsonModel 设置json格式
     */
    public ValidateModel(String jsonModel) {
        this.url = null;
        this.msg_key = null;
        this.jsonModel = jsonModel;
        this.returnJSON = true;
    }
    
    /**
     * 设置返回 的视图及带错误的key
     * @param url
     * @param msg_key 
     */
    public ValidateModel(String url, String msg_key) {
        this.url = url + (url.contains(".") ? "" : WebContext.getWebContext().webConfig.HM_SUFFIX);
        this.msg_key = (null == msg_key || msg_key.isEmpty()) ? VALIDATE_MSG_KEY : msg_key;
        this.jsonModel = null;
        this.returnJSON = false;
    }

    final public Map<String, ValidateFieldModel> getValidateFieldModel() {
        return this.map;
    }

    public abstract void iniValidate();

    final public ValidateModel put(String name, String regex, String msg, boolean ismust) {
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
        if (returnJSON) {
            jw.printOne(vr.getMsgByJson());
            return;
        }
        jw.request.setAttribute(msg_key, vr.getMsgByMap());
        jw.forward(url);
    }

    /**
     * 复验失败
     *
     * @param jw
     * @param vr
     */
    public void errorRecheck(JWeb jw, ValidateResultModel vr) {
        if (returnJSON) {
            jw.printOne(vr.getMsgByJson());
            return;
        }
        jw.request.setAttribute(msg_key, vr.getMsgByMap());
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
