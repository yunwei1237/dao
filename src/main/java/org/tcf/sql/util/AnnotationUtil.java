package org.tcf.sql.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 与注解相关的工具类
 * @author Archer Tan
 *
 */
public class AnnotationUtil {
	/**
	 * 获得注解对应的值
	 * 如：@Column 中的name值
	 * @param entityAnno
	 * @param methodName
	 * @return
	 */
	public static Object getAnnotationVal(Annotation entityAnno,String methodName){
		Method method;
		try {
			method = entityAnno.getClass().getMethod(methodName);
			return method.invoke(entityAnno);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
