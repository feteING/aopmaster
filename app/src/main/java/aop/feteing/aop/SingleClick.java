package aop.feteing.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author star
 * 防止多次点击多次执行
 * @date 2018/1/29
 */
//@Target表示这个注解只能给函数使用
//@Retention表示注解内容需要包含在Class字节码里，属于运行时需要的。
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleClick {


}
