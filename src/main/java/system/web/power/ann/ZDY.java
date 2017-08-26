package system.web.power.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import system.web.power.interfaces.IZDY;

/**
 * 授权类管理
 * @author wangchunzi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface ZDY {
    public Class<?extends IZDY> zdy() ;//授权码
    public String value();
}
