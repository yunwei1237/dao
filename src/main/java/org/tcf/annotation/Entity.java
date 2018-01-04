package org.tcf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
	/**
	 * 指定对应的数据库表名，默认为实体的名字
	 * @return
	 */
	String table() default "";
	/**
	 * 对应数据库名，默认为空
	 * @return
	 */
	String catelog() default "";
	/**
	 * 对应数据库的模式名,默认为空
	 * @return
	 */
	String schema() default "";
}
