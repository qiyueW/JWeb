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
    private final boolean urlIsEmpty;
    private final String key;
    
    public ValidateResultModel(boolean urlIsEmpty,String key) {
        this.urlIsEmpty = urlIsEmpty;
        this.key=key;
    }

    public ValidateResultModel put(final String name, final String msg) {

//        if (urlIsEmpty) {
        json.append(",{\"").append(name).append("\":\"").append(msg).append("\"}");
//            return this;
//        }
        map.put(name, msg);
        return this;

    }

    /**
     * 当t为String类时，返回json数据。为Map<String,String>时，返回Map数据
     *
     * @param <T>
     * @param t
     * @return
     */
    public <T> T getErrorMsg(Class<T> t) {
        return (T) (this.urlIsEmpty ? "[" + json.substring(1) + "]" : this.map);
    }

    public boolean isError() {
        return json.length() > 0 || map.size() > 0;
    }

    public Map<String, String> getMsgByMap() {
        return this.map;
    }

    public String getMsgByJson() {
        return this.key.isEmpty()?"[" + json.substring(1) + "]":"{\""+this.key+"\":["+json.substring(1) + "]}";
    }

}
