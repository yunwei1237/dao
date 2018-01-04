package org.tcf.sql.util;

import java.util.List;

import org.tcf.sql.entity.ColumnInfo;
import org.tcf.sql.entity.Order;
import org.tcf.sql.entity.OrderType;
import org.tcf.sql.entity.exp.Expression;

public class SqlUtil {
	public static String getOderBy(Order...orders){
		if(orders ==null || orders.length == 0) 
			return "";
		StringBuffer result = new StringBuffer("order by ");
		for(Order order:orders){
			result.append(String.format("%s %s,", getColumnString(order.getColumn()),order.getOrderType() == OrderType.desc?"desc":"asc"));
		}
		return StringUtil.trimEnd(result.toString(), ",");
	}
	public static String getGroupBy(String...groups){
		if(groups ==null || groups.length == 0) 
			return "";
		String result = "group by ";
		for(String group:groups){
			result += group+",";
		}
		return StringUtil.trimEnd(result, ",");
	}
	public static String getSelect(List<ColumnInfo> list){
		StringBuffer select = new StringBuffer();
		if(list != null && list.size()>0){
			select.append("select ");
			for(ColumnInfo column:list){
				select.append(getColumnString(column)+",");
			}
		}else{//如果不指定列，代表查询全部
			select.append("select *");
		}
		return StringUtil.trimEnd(select.toString(), ",");
	}
	public static String getFrom(String catelog,String table){
		return String.format("from %s%s", catelog,table);
	}
	public static String getHaving(Expression having){
		return having !=null ? "having "+having.get().getSql():"";
	}
	public static String getWhere(Expression where){
		return where !=null ? "where "+where.get().getSql():"";
	}
	/**
	 * 获得列的字符串形式，并检查该列是否有函数修饰，如果有，就显示出来<br />
	 * 有函数，如：avg(age)<br />
	 * 无函数，如：age <br />
	 * @param column
	 * @return
	 */
	public static String getColumnString(ColumnInfo column){
		if(column.getFunction() != null){//如果该列存在一个函数，就使用函数，如：avg(age)
			return column.getFunction().get(column.getName());
		}else{//没有函数，如：age,
			return column.getName();
		}
	}
}
