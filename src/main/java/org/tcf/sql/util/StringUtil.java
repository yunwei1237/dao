package org.tcf.sql.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringUtil {
	/**
	 * 去除字符串的前缀
	 * 	and loginPwd = ? and studentName = ?   
	 *	or loginPwd = ? or studentName = ?   
	 * @param str
	 * @param prefixes
	 * @return
	 */
	public static String trimBegin(String str,String...prefixes){
		String result  = null;
		for(String prefix : prefixes){
			if(str.trim().startsWith(prefix)){
				int index = str.indexOf(prefix);
				result = str.substring(index+prefix.length());
				//str += str.re
				break;
			}
		}
		return result;
	}
	/**
	 * 去除字符串的后缀
	 * @param str
	 * @param subfix
	 * @return
	 */
	public static String trimEnd(String str,String subfix){
		int index = str.lastIndexOf(subfix);
		if(index != -1)
			return str.substring(0,index);
		return str;
	}
	/**
	 * 判断字符串是否为null或''
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return str.trim().length()==0 || str == null;
	}
	/**
	 * 将一个集合打包成一个字符串
	 * 
	 * @param obj 可以是list,set,map,Object[],
	 * @param prefix
	 * @param subfix
	 * @param delimiter
	 * @return
	 */
	public static String packing(Object obj,String prefix,String subfix,String delimiter){
		StringBuffer str = new StringBuffer();
		if(obj instanceof List || obj instanceof Set){
			Collection collect = (Collection) obj;
			for(Object o : collect){
				str.append(String.format("%s%s", o,delimiter));
			}
		}else if(obj instanceof Map){
			Map map = (Map) obj;
			for(Object o : map.values()){
				str.append(String.format("%s%s", o,delimiter));
			}
		}else if(obj instanceof Object[]){
			Object[] arr = (Object[]) obj;
			for(Object o : arr){
				str.append(String.format("%s%s", o,delimiter));
			}
		}else{
			str.append(obj.toString());
		}
		return String.format("%s%s%s", prefix,StringUtil.trimEnd(str.toString(), delimiter),subfix);
	}
}
