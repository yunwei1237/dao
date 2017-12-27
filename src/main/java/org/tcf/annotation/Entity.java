package org.tcf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
	/**
	 * ָ����Ӧ�����ݿ������Ĭ��Ϊʵ�������
	 * @return
	 */
	String table() default "";
	/**
	 * ��Ӧ���ݿ�����Ĭ��Ϊ��
	 * @return
	 */
	String catelog() default "";
}
