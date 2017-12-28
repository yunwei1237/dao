package org.tcf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标识一个实体的属性和数据库中的字段相关
 * @author Archer Tan
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	/**
	 * 对应字段的名称，默认为实体属性的名称
	 * @return
	 */
	String name() default "";
	/**
	 * 字段的长度（字符和数字）
	 * @return
	 */
	int length() default 0;
	/**
	 * 字段的小数位
	 * @return
	 */
	int scale() default 0;
	/**
	 * 是否不允许为null，默认false，也就是允许为null
	 * @return
	 */
	boolean notNull() default false;
}
