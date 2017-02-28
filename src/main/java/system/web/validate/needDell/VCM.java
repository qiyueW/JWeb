package system.web.validate.needDell;

import java.util.Map;
import system.web.JWeb;
import system.web.validate.model.ValidateFieldModel;
import system.web.validate.model.ValidateResultModel;
import system.web.validate.needDell.regex.VRegex;

/**
 *
 * @author wangchunzi
 */
public abstract class VCM {

    abstract public void setHead(VHeadConfig head);

    abstract public void setRegex(VRegex obj);

    /**
     * 自定义复验
     *
     * @param jw
     * @param map
     * @param vr
     * @return
     */
    abstract public ValidateResultModel recheck(JWeb jw, Map<String, ValidateFieldModel> map, ValidateResultModel vr);
//
//    /**
//     * 校验失败
//     *
//     * @param jw
//     * @param vr
//     */
//    public void error(JWeb jw, ValidateResultModel vr) {
//        if (returnJSON) {
//            jw.printOne(vr.getMsgByJson());
//            return;
//        }
//        jw.request.setAttribute(msg_key, vr.getMsgByMap());
//        jw.forward(url);
//    }
//
//    /**
//     * 复验失败
//     *
//     * @param jw
//     * @param vr
//     */
//    public void errorRecheck(JWeb jw, ValidateResultModel vr) {
//        if (returnJSON) {
//            jw.printOne(vr.getMsgByJson());
//            return;
//        }
//        jw.request.setAttribute(msg_key, vr.getMsgByMap());
//        jw.forward(url);
//    }

    /**
     * 成功
     *
     * @param jw
     * @param vr
     */
    public void success(JWeb jw, ValidateResultModel vr) {

    }
}
