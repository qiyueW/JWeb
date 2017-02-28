package system.web.validate.needDell.regex;

import java.util.Map;
import system.web.validate.model.ValidateFieldModel;

/**
 *
 * @author wangchunzi
 */
final public class VRegex {

    private int must_count = 0;
    Map<String, ValidateFieldModel> map;

    final public VRegex put(String fieldName, String regex, String error, boolean isMust) {
        map.put(fieldName, new ValidateFieldModel(fieldName, regex, error, isMust));
        if (isMust) {
            must_count++;
        }
        return this;
    }

    final int getMustValidateCount() {
        return this.must_count;
    }

    final void set(Map<String, ValidateFieldModel> map) {
        this.map = map;
    }
}
