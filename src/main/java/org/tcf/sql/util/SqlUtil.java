package org.tcf.sql.util;

import java.util.List;

import org.tcf.sql.entity.ColumnInfo;
import org.tcf.sql.entity.Order;
import org.tcf.sql.entity.OrderType;
import org.tcf.sql.entity.exp.Expression;

public class SqlUtil {
	public static String getOderBy(List<Order> orders){
		if(orders ==null || orders.size() == 0) 
			return "";
		StringBuffer result = new StringBuffer("order by ");
		for(Order order:orders){
			result.append(String.format("%s %s,", order.getColumn().getColumnString(),order.getOrderType() == OrderType.desc?"desc":"asc"));
		}
		return StringUtil.trimEnd(result.toString(), ",");
	}
	public static String getGroupBy(List<ColumnInfo> groups){
		if(groups ==null || groups.size() == 0) 
			return "";
		StringBuffer result = new StringBuffer("group by ");
		for(ColumnInfo group:groups){
			result.append(group.getColumnString()+",");
		}
		return StringUtil.trimEnd(result.toString(), ",");
	}
	public static String getSelect(List<ColumnInfo> list){
		StringBuffer select = new StringBuffer();
		if(list != null && list.size()>0){
			select.append("select ");
			for(ColumnInfo column:list){
				select.append(column.getColumnString()+",");
			}
		}else{//如果不指定列，代表查询全部
			select.append("select *");
		}
		return StringUtil.trimEnd(select.toString(), ",");
	}
	public static String getFrom(String catelog,String table){
		if(catelog != null)
			return String.format("from %s.%s", catelog,table);
		else
			return String.format("from %s",table);
	}
	public static String getHaving(Expression having){
		return having !=null ? "having "+having.get().getSql():"";
	}
	public static String getWhere(Expression where){
		return where !=null ? "where "+where.get().getSql():"";
	}
	
}
