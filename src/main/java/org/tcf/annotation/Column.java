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
	 * 字段的长度
	 * @return
	 */
	int length() default 0;
	
	
}
