package org.tcf.sql.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionUtil {
	
	/**
	 * 判断对象是否是list,map,Object[],Set
	 * @param obj
	 * @return
	 */
	public static boolean isCollection(Object obj){
		if(obj instanceof List 
				|| obj instanceof Map
				|| obj instanceof Object[]
				|| obj instanceof byte[]
				|| obj instanceof short[]
				|| obj instanceof int[]
				|| obj instanceof long[]
				|| obj instanceof double[]
				|| obj instanceof float[]
				|| obj instanceof boolean[]
				|| obj instanceof char[]){
			return true;
		}
		return false;
	}
	/**
	 * 检查集合中是否包含某个字符串
	 * @param collect
	 * @param str
	 * @return
	 */
	public static boolean contains(Collection<String> collect,String str){
		for(String obj:collect){
			if(obj.equals(str)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 检查集合中是否包含某个字符串，忽略大小写
	 * @param collect
	 * @param str
	 * @return
	 */
	public static boolean containsIgnoreCase(Collection<String> collect,String str){
		for(String obj:collect){
			if(obj.equalsIgnoreCase(str)){
				return true;
			}
		}
		return false;
	}
	
	public static List getList(Object...objs){
		List result = new ArrayList();
		for(Object obj:objs){
			result.add(obj);
		}
		return result;
	}
	public static List getList(List...lists){
		List result = new ArrayList();
		for(List list:lists){
			if(list != null)
				result.addAll(list);
		}
		return result;
	}
	public static Map getMap(Map...maps){
		Map result = new HashMap();
		for(Map map:maps){
			if(map != null)
				result.putAll(map);
		}
		return result;
	}
}
