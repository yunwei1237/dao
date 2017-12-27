package org.tcf.sql.util;

import org.tcf.sql.entity.Order;
import org.tcf.sql.entity.OrderType;

public class SqlUtil {
	public static String getOderBy(Order...orders){
		if(orders ==null || orders.length == 0) 
			return "";
		String result = " order by ";
		for(Order order:orders){
			result += String.format("%s %s,", order.getColumn(),order.getOrderType() == OrderType.desc?"desc":"asc");
		}
		return result;
	}
	public static String getGroupBy(String...groups){
		if(groups ==null || groups.length == 0) 
			return "";
		String result = " group by ";
		for(String group:groups){
			result += group+",";
		}
		return result;
	}
}
