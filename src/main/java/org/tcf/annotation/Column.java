package org.tcf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ���ڱ�ʶһ��ʵ������Ժ����ݿ��е��ֶ����
 * @author Archer Tan
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	/**
	 * ��Ӧ�ֶε����ƣ�Ĭ��Ϊʵ�����Ե�����
	 * @return
	 */
	String name() default "";
	/**
	 * �ֶεĳ���
	 * @return
	 */
	int length() default 0;
	
	
}
