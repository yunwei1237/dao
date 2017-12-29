package org.tcf.sql.util;

import org.tcf.sql.entity.Condition;
import org.tcf.sql.entity.ConditionType;

public class ConditionUtil {
	public static Condition not(Condition condition){
		
		return condition;
	}
	public static String get(Condition condition) throws Exception{
		return get(condition.getExpress().get(),condition.getNext());
	}
	private static String get(String str,Condition condition) throws Exception{
		if(condition.getConditionType() == ConditionType.and 
				|| condition.getConditionType() == ConditionType.or){
			if(condition.getNext() == null)
				throw new Exception(String.format("%s逻辑操作符需要两个表达式", condition.getConditionType()));
			str += String.format("%s %s", condition.getConditionType(),condition.getNext().getExpress());
		}else{
			if(condition.getNext() != null)
				throw new Exception(String.format("%s逻辑操作符只能有一个表达式", condition.getConditionType()));
			str = String.format("%s %s",condition.getConditionType(),str);
		}
		if(condition.getNext() != null){
			get(str,condition.getNext());
		}
		return str;
	}
}
