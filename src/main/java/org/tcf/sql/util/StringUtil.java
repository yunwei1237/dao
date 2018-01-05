package org.tcf.sql.util;

import java.util.Arrays;
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
		}else if(obj instanceof int[]){
			int[] arr = (int[]) obj;
			for(int o : arr){
				str.append(String.format("%s%s", o,delimiter));
			}
		}else if(obj instanceof byte[]){
			byte[] arr = (byte[]) obj;
			for(byte o : arr){
				str.append(String.format("%s%s", o,delimiter));
			}
		}else if(obj instanceof short[]){
			short[] arr = (short[]) obj;
			for(short o : arr){
				str.append(String.format("%s%s", o,delimiter));
			}
		}else if(obj instanceof long[]){
			long[] arr = (long[]) obj;
			for(long o : arr){
				str.append(String.format("%s%s", o,delimiter));
			}
		}else if(obj instanceof char[]){
			char[] arr = (char[]) obj;
			for(char o : arr){
				str.append(String.format("%s%s", o,delimiter));
			}
		}else if(obj instanceof boolean[]){
			boolean[] arr = (boolean[]) obj;
			for(boolean o : arr){
				str.append(String.format("%s%s", o,delimiter));
			}
		}else if(obj instanceof double[]){
			double[] arr = (double[]) obj;
			for(double o : arr){
				str.append(String.format("%s%s", o,delimiter));
			}
		}else if(obj instanceof float[]){
			float[] arr = (float[]) obj;
			for(float o : arr){
				str.append(String.format("%s%s", o,delimiter));
			}
		}else{
			str.append(obj.toString());
		}
		return String.format("%s%s%s", prefix,StringUtil.trimEnd(str.toString(), delimiter),subfix);
	}
	/**
	 * 将一个集合打包成一个字符串
	 * @param obj 可以是list,set,map,Object[],
	 * @param delimiter 分隔符
	 * @return
	 */
	public static String packing(Object obj,String delimiter){
		return packing(obj, "", "", delimiter);
	}
	public static String getParamsInfo(Object ... params){
		StringBuffer sb = new StringBuffer();
		if(params != null && params.length>0){
			for(int i = 0;i<params.length;i++){
				Object obj = params[i];
				sb.append(String.format("%s => %s,",obj.getClass().getSimpleName(),obj));
			}
			return String.format("size:%s ", params.length)+"["+StringUtil.trimEnd(sb.toString(), ",")+"]";
		}
		return String.format("size:%s ", 0)+"[]";
	}
	public static String listInfo(List list){
		StringBuffer sb = new StringBuffer();
		for(Object obj:list){
			sb.append(toString(obj)+"\n");
		}
		return sb.toString();
	}
	
	public static String toString(Object obj){
		String type = obj.getClass().getName();
		String val = null;
		if("java.sql.Date".equals(type)){
			val = ((java.sql.Date)obj).toLocaleString();
		}else if("java.util.Date".equals(type)){
			val = ((java.util.Date)obj).toLocaleString();
		}else if(obj instanceof Object[]){
			val = Arrays.toString((Object[])obj);
		}else if(obj instanceof byte[]){
			val = Arrays.toString((byte[])obj);
		}else if(obj instanceof short[]){
			val = Arrays.toString((short[])obj);
		}else if(obj instanceof int[]){
			val = Arrays.toString((int[])obj);
		}else if(obj instanceof long[]){
			val = Arrays.toString((long[])obj);
		}else if(obj instanceof double[]){
			val = Arrays.toString((double[])obj);
		}else if(obj instanceof float[]){
			val = Arrays.toString((float[])obj);
		}else if(obj instanceof boolean[]){
			val = Arrays.toString((boolean[])obj);
		}else if(obj instanceof char[]){
			val = Arrays.toString((char[])obj);
		}else{
			val = obj.toString();
		}
		return val;
	}
}
