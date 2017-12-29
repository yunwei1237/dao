package org.tcf.sql.util;

import org.tcf.sql.entity.Expression;
import org.tcf.sql.entity.Operation;

public class ExpUtil {
	public static Expression eq(String name,Object value){
		return new Expression(name, value, Operation.eq);
	}
	public static Expression ne(String name,Object value){
		return new Expression(name, value, Operation.ne);
	}
	public static Expression lt(String name,Object value){
		return new Expression(name, value, Operation.lt);
	}
	public static Expression gt(String name,Object value){
		return new Expression(name, value, Operation.gt);
	}
	public static Expression le(String name,Object value){
		return new Expression(name, value, Operation.le);
	}
	public static Expression ge(String name,Object value){
		return new Expression(name, value, Operation.ge);
	}
	public static Expression in(String name,Object value){
		return new Expression(name, value, Operation.in);
	}
	public static Expression like(String name,Object value){
		return new Expression(name, value, Operation.like);
	}
}
