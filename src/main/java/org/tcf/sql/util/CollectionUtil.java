package org.tcf.sql.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
				|| obj instanceof Set){
			return true;
		}
		return false;
	}
}
