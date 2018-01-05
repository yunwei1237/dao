package org.tcf.sql.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.tcf.sql.entity.exp.Expression;

/**
 * 保存sql的相关信息，sql语句和需要的参数名集合
 * @author Archer Tan
 *
 */
public class SqlInfo {
	/**
	 * 用于保存生成的sql语句
	 */
	private String sql;
	/**
	 * 用于保存生成sql语句时需要传入参数的<k:字段名,v:表达式中对应的值><br />
	 * 记录哪些字段需要传值,以便收集相关的参数值
	 */
	private List<String> keyList = new ArrayList<String>();
	private List<Object> values = new ArrayList<Object>();
	
	public SqlInfo(String sql, List<String> keyList, List<Object> values) {
		super();
		this.sql = sql;
		this.addPlaceholders(keyList, values);
	}
	public SqlInfo(String sql, Expression...exps) {
		super();
		this.sql = sql;
		this.addPlaceholders(exps);
	}
	public SqlInfo() {
		super();
	}
	/**
	 * 创建一个SqlInfo对象，并增加一个占位符
	 * @param sql
	 * @param key
	 * @param val
	 */
	public SqlInfo(String sql, String key,Object val) {
		super();
		this.sql = sql;
		this.addPlaceholder(key, val);
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public List<String> getKeyList() {
		return keyList;
	}
	public void setKeyList(List<String> keyList) {
		this.keyList = keyList;
	}
	public List<Object> getValues() {
		return values;
	}
	public void setValues(List<Object> values) {
		this.values = values;
	}
	/**
	 * 增加一个占位符
	 * 每一个占位符（？）对应一个placeholder
	 * @param key 所有的字段会自动变成小写，（列不区分大小写）
	 * @param val
	 */
	public void addPlaceholder(String key,Object val){
		this.keyList.add(key.toLowerCase());
		this.values.add(val);
	}
	/**
	 * 增加多个占位符
	 * 每一个占位符（？）对应一个placeholder
	 * @param placeholders
	 */
	public void addPlaceholders(List<String> keyset,List<Object> values){
		for(int i = 0;i<keyset.size();i++){
			this.addPlaceholder(keyset.get(i), values.get(i));
		}
	}
	/**
	 * 将表达式中的Sql信息添加到本Sql信息（包含：keyList和values）
	 * @param exps
	 * @throws Exception
	 */
	public void addPlaceholders(Expression...exps){
		for(Expression exp:exps){
			if(exp != null){
				SqlInfo info = exp.get();
				this.addPlaceholders(info.getKeyList(), info.getValues());
			}
		}
	}
	
	/**
	 * 根据提供的列，获得所有占位符的参数
	 * @param columns
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object[] getValues(Map<String,Object> columns){
		List list = new ArrayList();
		for(int i = 0;i<keyList.size();i++){
			String key = keyList.get(i);
			Object val = null;
			if(columns != null)
				val = values.get(i) == null?columns.get(key):values.get(i);
			else
				val = values.get(i);
			if(val != null)
				list.add(val);
		}
		return list.toArray();
	}
}
