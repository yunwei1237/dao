package org.tcf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
	/**
	 * 指定主键的生成策略
	 * @return
	 */
	PrimaryKeyType type() default PrimaryKeyType.sequence;
	/**
	 * 指定序列的名称,type为PrimaryKeyType.sequence时指定
	 */
	String sequenceName() default "";
}
