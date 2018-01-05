package org.tcf.sql.util;

import java.util.List;

import org.tcf.sql.entity.LikeType;
import org.tcf.sql.entity.Operation;
import org.tcf.sql.entity.exp.ExpLogic;
import org.tcf.sql.entity.exp.ExpValue;
import org.tcf.sql.entity.exp.Expression;

public class ExpUtil {
	/**
	 * 生成<等于>表达式
	 * 如：age = 12
	 * @param name
	 * @param value
	 * @return
	 */
	public static ExpValue eq(String name,Object value){
		return new ExpValue(name, value, Operation.eq);
	}
	public static ExpValue ne(String name,Object value){
		return new ExpValue(name, value, Operation.ne);
	}
	public static ExpValue lt(String name,Object value){
		return new ExpValue(name, value, Operation.lt);
	}
	public static ExpValue gt(String name,Object value){
		return new ExpValue(name, value, Operation.gt);
	}
	public static ExpValue le(String name,Object value){
		return new ExpValue(name, value, Operation.le);
	}
	public static ExpValue ge(String name,Object value){
		return new ExpValue(name, value, Operation.ge);
	}
	public static ExpValue in(String name,Object value){
		return new ExpValue(name, value, Operation.in);
	}
	public static ExpValue like(String name,String value,LikeType type){
		if(type == LikeType.start){
			value = value + "%";
		}else if(type == LikeType.end){
			value = "%" + value;
		}else{
			value = "%" + value + "%";
		}
		return new ExpValue(name, value, Operation.like);
	}
	public static ExpValue like(String name,String value){
		return like(name, value,LikeType.containt);
	}
	/**
	 * 将所有表达式以and方式连接
	 * @param exps
	 * @return
	 */
	public static Expression and(ExpValue...exps){
		Expression exp = exps[0];
		for(int i = 1;i<exps.length;i++){
			exp = ExpLogic.and(exp, exps[i]);
		}
		return exp;
	}
	public static Expression and(List<ExpValue> exps){
		Expression exp = exps.get(0);
		for(int i = 1;i<exps.size();i++){
			exp = ExpLogic.and(exp, exps.get(i));
		}
		return exp;
	}
	/**
	 * 将所有表达式以or方式连接
	 * @param exps
	 * @return
	 */
	public static Expression or(ExpValue...exps){
		Expression exp = exps[0];
		for(int i = 1;i<exps.length;i++){
			exp = ExpLogic.or(exp, exps[i]);
		}
		return exp;
	}
	public static Expression or(List<ExpValue> exps){
		Expression exp = exps.get(0);
		for(int i = 1;i<exps.size();i++){
			exp = ExpLogic.or(exp, exps.get(i));
		}
		return exp;
	}
	/**
	 * 将表达式取反
	 * @param exp
	 * @return
	 */
	public static Expression not(Expression exp){
		return ExpLogic.not(exp);
	}
}
