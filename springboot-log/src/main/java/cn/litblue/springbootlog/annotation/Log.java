package cn.litblue.springbootlog.annotation;

import cn.litblue.springbootlog.util.IpUtil;

import java.lang.annotation.*;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/13  20:46
 */
// 说明了Annotation所修饰的对象范围
@Target(ElementType.METHOD)
// 定义了该Annotation被保留的时间长短
@Retention(RetentionPolicy.RUNTIME)
// 用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。
@Documented
public @interface Log {

    /**
     * 日志操作名称
     */
    String name() default "";

    /**
     * 描述
     */
    String description() default "";

}
