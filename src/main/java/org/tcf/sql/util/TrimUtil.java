package org.tcf.sql.util;

public class TrimUtil {
	// and loginPwd = ? and studentName = ?   
	// or loginPwd = ? or studentName = ?   
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
	public static String trimEnd(String str,String subfix){
		int index = str.lastIndexOf(subfix);
		if(index != -1)
			return str.substring(0,index);
		return "";
	}
}
