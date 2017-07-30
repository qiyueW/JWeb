package system.web.power.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import system.web.power.PDK;

/**
 * 授权类管理
 * @author wangchunzi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface SQ {
    public String value() ;//授权码
    public String scope()default PDK.SESSION_DEFAULT_KEY;
}
