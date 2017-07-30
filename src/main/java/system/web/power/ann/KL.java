package system.web.power.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 口令类管理
 * @author wangchunzi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface KL {
    public String value() default "";//口令分类、等级 等等，主要作用于需要区分的场境
    public String scope()default ""; //是否需要登陆
}
