package system.web.validate.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wangchunzi
 */
public class ValidateModelData {

    private static final Map<Class, ValidateModel> validate = new HashMap();

    /**
     * 每个url请求映射的校验类
     *
     * @param c
     * @return
     */
    public static final ValidateModel getValidateModel(Class c) {
        return validate.get(c);
    }

    public void putMap(Class c, ValidateModel vdm) {
//        System.out.println("校验映射：" + c.getSimpleName() + "   *" + vdm.getClass().getName());
        validate.put(c, vdm);
    }

    public ValidateModel get(Class<? extends ValidateModel> c) {
        return validate.get(c);
    }

}
