package org.tcf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
	/**
	 * 对应字段的名称，默认为实体属性的名称
	 * @return
	 */
	String name() default "";
	/**
	 * 指定主键的生成策略
	 * @return
	 */
	PrimaryKeyType type() default PrimaryKeyType.assigned;
	/**
	 * 指定序列的名称
	 */
	String sequenceName() default "";
}
