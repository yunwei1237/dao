package org.tcf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于指定一个列为主键
 * @author Archer Tan
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
	/**
	 * 指定主键的生成策略,默认为PrimaryKeyType.assigned
	 * @return
	 */
	PrimaryKeyType type() default PrimaryKeyType.assigned;
	/**
	 * 指定序列的名称,type为PrimaryKeyType.sequence时指定
	 */
	String sequenceName() default "";
}
