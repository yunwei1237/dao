package org.tcf.sql.generator.impl;

import java.util.List;

import org.tcf.annotation.PrimaryKeyType;
import org.tcf.sql.entity.ColumnInfo;
import org.tcf.sql.entity.EntityInfo;
import org.tcf.sql.entity.Order;
import org.tcf.sql.entity.SqlInfo;
import org.tcf.sql.entity.exp.Expression;
import org.tcf.sql.generator.SqlGenerator;
import org.tcf.sql.util.SqlUtil;
import org.tcf.sql.util.StringUtil;

import com.tcf.exception.DaoException;

public class MySqlSqlGenerator implements SqlGenerator {

	@Override
	public SqlInfo genertInsert(String catelog,String schema,String table,List<ColumnInfo> columnInfos,PrimaryKeyType type) {
		SqlInfo sqlInfo = new SqlInfo();
		if(columnInfos == null || columnInfos.size() == 0)
			throw new DaoException("columnInfos不能为空");
		StringBuffer columns = new StringBuffer();
		StringBuffer values = new StringBuffer();
		for(ColumnInfo column:columnInfos){
			//如果主键类型是自动增长，就指定类型为default
			if(column.getPrimaryKey() 
					&& type == PrimaryKeyType.autoIncrement){
				continue;
			}else{
				columns.append(column.getName()+",");
				values.append("?,");
				sqlInfo.addPlaceholder(column.getName(), null);
			}
		}
		String sql = String.format("insert into %s%s(%s) values(%s)", catelog,
				table,StringUtil.trimEnd(columns.toString(), ","),StringUtil.trimEnd(values.toString(), ","));
		sqlInfo.setSql(sql);
		return sqlInfo;
	}

	@Override
	public SqlInfo genertUpdate(String catelog,String schema,String table,List<ColumnInfo> columnInfos,Expression where) {
		SqlInfo sqlInfo = new SqlInfo();
		if(columnInfos == null || columnInfos.size() == 0)
			throw new RuntimeException("EntityInfo.columns不能为空");
		StringBuffer sets = new StringBuffer();
		for(ColumnInfo column:columnInfos){
			//设置非主键的列
			if(!column.getPrimaryKey()){
				sets.append(String.format("%s = ?,", column.getName()));
				sqlInfo.addPlaceholder(column.getName(), null);
			}
		}
		String sql = String.format("update %s%s set %s where %s", catelog,
				table,StringUtil.trimEnd(sets.toString(), ","),where.get().getSql());
		sqlInfo.addPlaceholders(where);
		sqlInfo.setSql(sql);
		return sqlInfo;
	}

	@Override
	public SqlInfo genertDelete(String catelog,String schema,String table,Expression where) {
		String sql = String.format("delete from %s%s where %s", catelog,
				table,where.get().getSql());
		return new SqlInfo(sql,where);
	}

	@Override
	public SqlInfo genertSelect(String catelog,String schema,String table,List<ColumnInfo> columnInfos,Expression where,List<ColumnInfo> groups,Expression having,List<Order> orders,Integer begin,Integer size) {
		SqlInfo sqlInfo = new SqlInfo();
		String selectStr = SqlUtil.getSelect(columnInfos);
		String fromStr = SqlUtil.getFrom(catelog, table);
		String whereStr = SqlUtil.getWhere(where);
		String groupStr = SqlUtil.getGroupBy(groups);
		String havingStr = SqlUtil.getHaving(having);
		String orderStr = SqlUtil.getOderBy(orders);
		String limitStr = getLimit(begin, size);
		String sql = String.format("%s %s %s %s %s %s %s", selectStr,
				fromStr,whereStr,groupStr,havingStr,orderStr,limitStr);
		sqlInfo.addPlaceholders(where);
		sqlInfo.addPlaceholders(having);
		sqlInfo.setSql(sql);
		return sqlInfo;
	}
	private String getLimit(Integer begin,Integer size){
		if(begin == null) begin = 0;
		if(size == null) size = 0;
		if(begin != 0 && size == 0)
			throw new DaoException("size为0或为null时查询不到任何信息");
		//如果begin和size都为0说明不需要限制
		if(begin == 0 && size == 0)
			return "";
		return String.format("limit %s,%s", begin,size);
	}
}
