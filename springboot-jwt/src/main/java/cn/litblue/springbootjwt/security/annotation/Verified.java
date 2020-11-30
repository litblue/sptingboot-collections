package cn.litblue.springbootjwt.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/15  13:59
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Verified {

    boolean required() default true;
}
