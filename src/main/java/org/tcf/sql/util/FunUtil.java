package org.tcf.sql.util;

import org.tcf.sql.entity.fun.Function;

public class FunUtil {
	/**
	 * 获得avg函数
	 * @param columnName
	 * @return
	 */
	public static Function avg(){
		return new Function("avg");
	}
	public static Function max(){
		return new Function("max");
	}
	public static Function min(){
		return new Function("min");
	}
	public static Function count(){
		return new Function("count");
	}
	public static Function sum(){
		return new Function("sum");
	}
}
