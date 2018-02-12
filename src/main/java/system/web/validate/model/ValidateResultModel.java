package system.web.validate.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wangchunzi
 */
public class ValidateResultModel {

    private final StringBuilder json = new StringBuilder();
    private final Map<String, String> map = new HashMap();
    private final boolean returnJSON;
    private final String key;
    private final String jsonModel;

    public ValidateResultModel(final boolean returnJSON, final String key, final String str) {
        this.returnJSON = returnJSON;
        this.key = key;
        this.jsonModel = str;
    }

    public ValidateResultModel put(final String name, final String msg) {

        if (this.returnJSON) {
            json.append(",\"").append(name).append("\":\"").append(msg).append("\"");
            return this;
        }
        map.put(name, msg);
        return this;

    }

    /**
     * 当t为String类时，返回json数据。为Map时，返回Map数据
     *
     * @param <T>
     * @param t
     * @return
     */
    public <T> T getErrorMsg(Class<T> t) {
        return (T) (this.returnJSON ? "{" + json.substring(1) + "}" : this.map);
    }

    public boolean isError() {
        return json.length() > 0 || map.size() > 0;
    }

    public Map<String, String> getMsgByMap() {
        return this.map;
    }

    public String getMsgByJson() {
        return this.jsonModel.replaceFirst("#", "{" + json.substring(1) + "}");
    }

}
