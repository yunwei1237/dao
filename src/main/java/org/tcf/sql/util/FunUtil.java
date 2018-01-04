package org.tcf.sql.util;

import org.tcf.sql.entity.fun.Function;

public class FunUtil {
	/**
	 * 获得avg函数
	 * @param columnName
	 * @return
	 */
	public Function avg(){
		return new Function("avg");
	}
	public Function max(){
		return new Function("max");
	}
	public Function min(){
		return new Function("min");
	}
	public Function count(){
		return new Function("count");
	}
	public Function sum(){
		return new Function("sum");
	}
}
