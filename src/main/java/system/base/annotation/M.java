package system.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface M {

    /**
     * 默认空“”
     *
     * @return 字符串
     */
    public String value() default "";//路径

    /**
     * 默认空“”
     *
     * @return 字符串
     */
    public String url() default "";//路径

}
