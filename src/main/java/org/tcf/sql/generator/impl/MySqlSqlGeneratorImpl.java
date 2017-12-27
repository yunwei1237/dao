package org.tcf.sql.generator.impl;

import java.util.List;

import org.tcf.annotation.PrimaryKeyType;
import org.tcf.sql.entity.ConditionType;
import org.tcf.sql.entity.EntityInfo;
import org.tcf.sql.entity.Order;
import org.tcf.sql.generator.SqlGenerator;
import org.tcf.sql.util.SqlUtil;
import org.tcf.sql.util.TrimUtil;


public class MySqlSqlGeneratorImpl implements SqlGenerator {

	@Override
	public String genertInsert(EntityInfo info) throws Exception {
		// TODO Auto-generated method stub
		String fields = "";
		String values = "";
		//id
		if(info.getType() == PrimaryKeyType.assigned){
			if(info.getIdVal() != null){
				fields += info.getId() + ",";
				values += "?,";
			}
		}else if(info.getType() == PrimaryKeyType.autoIncrement){
			if(info.getIdVal() != null){
				fields += info.getId() + ",";
				values += "default,";
			}
		}
		
		//列
		for(String column:info.getColumns()){
			fields += column + ",";
			values += "?,";
		}
		return String.format("insert into %s%s(%s) values(%s)", info.getCatelog(),info.getTable(),TrimUtil.trimEnd(fields, ","),TrimUtil.trimEnd(values, ","));
	}

	@Override
	public String genertUpdate(EntityInfo info) throws Exception {
		// TODO Auto-generated method stub
		String sets = "";
		String conditions = "";
		for(String column:info.getColumns()){
			sets += column + " = ?,";
		}
		if(info.getIdVal() != null){
			conditions += info.getId()+" = ?,";
		}
		return String.format("update %s%s set %s where %s", info.getCatelog(),info.getTable(),TrimUtil.trimEnd(sets, ","),TrimUtil.trimEnd(conditions, ","));
	}

	@Override
	public String genertDelete(EntityInfo info) throws Exception {
		// TODO Auto-generated method stub
		String conditions = "";
		if(info.getIdVal() != null){
			conditions += info.getId()+" = ?,";
		}
		return String.format("delete from %s%s where %s", info.getCatelog(),info.getTable(),TrimUtil.trimEnd(conditions, ","));
	}
	/**
	 * order by stuname desc,age asc
	 */
	@Override
	public String genertSelect(EntityInfo info,ConditionType type,String[] likes,String[] groups,Order[] orders,Integer begin,Integer size) throws Exception {
		// TODO Auto-generated method stub
		String conditionType = type == ConditionType.and ?"and":"or";
		String conditions = "";
		String op = "=";
		if(info.getIdVal() != null){
			if(likes != null && checkLike(info.getId(),likes)){
				op = "like";
			}
			conditions += String.format("%s %s %s ? ", conditionType,info.getId(),op);
		}
		for(String column:info.getColumns()){
			
			if(likes != null && checkLike(column,likes)){
				op = "like";
			}
			conditions += String.format("%s %s %s ? ", conditionType,column,op);
		}
		return String.format("select * from %s%s where%s%s%s%s",
				info.getCatelog(),info.getTable(),TrimUtil.trimBegin(conditions, "and","or"),TrimUtil.trimEnd(SqlUtil.getGroupBy(groups), ","),TrimUtil.trimEnd(SqlUtil.getOderBy(orders),","),getLimit(begin,size));
	}
	
	private String getLimit(Integer begin,Integer size) throws Exception{
		if(begin == null) begin = 0;
		if(size == null) size = 0;
		if(begin != 0 && size == 0)
			throw new Exception("size为0或为null时查询不到任何信息");
		//如果begin和size都为0说明不需要限制
		if(begin == 0 && size == 0)
			return "";
		return String.format(" limit %s,%s", begin,size);
	}
	
	private boolean checkLike(String column,String[] likes){
		for(String like : likes){
			if(column.equalsIgnoreCase(like)){
				return true;
			}
		}
		return false;
	}

}
