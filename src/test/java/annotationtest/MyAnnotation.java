package annotationtest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 
 * @author xuwenjin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

	/** 作者 */
	String author() default "xuwj";

	/** 日期 */
	String date();

	/** 版本 */
	int revision() default 1;

	/** 评论 */
	String comments();

}
