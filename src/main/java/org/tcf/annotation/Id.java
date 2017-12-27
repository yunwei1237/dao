package org.tcf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
	/**
	 * ��Ӧ�ֶε����ƣ�Ĭ��Ϊʵ�����Ե�����
	 * @return
	 */
	String name() default "";
	/**
	 * ָ�����������ɲ���
	 * @return
	 */
	PrimaryKeyType type() default PrimaryKeyType.assigned;
	/**
	 * ָ�����е�����
	 */
	String sequenceName() default "";
}
