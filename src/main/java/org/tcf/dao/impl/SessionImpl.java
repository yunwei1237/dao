package org.tcf.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.tcf.dao.Session;
import org.tcf.sql.entity.ColumnInfo;
import org.tcf.sql.entity.NonQueryType;
import org.tcf.sql.entity.Order;
import org.tcf.sql.entity.exp.Expression;
import org.tcf.sql.util.EntityUtil;
import org.tcf.sql.util.SessionUtil;

import com.tcf.exception.DaoException;

public class SessionImpl implements Session {
	
	@Override
	public int save(Object obj){
		// TODO Auto-generated method stub
		return SessionUtil.update(obj,NonQueryType.insert);
	}

	@Override
	public int update(Object obj){
		// TODO Auto-generated method stub
		return SessionUtil.update(obj,NonQueryType.update);
	}

	@Override
	public int delete(Object obj){
		// TODO Auto-generated method stub
		return SessionUtil.update(obj,NonQueryType.delete);
	}

	@Override
	public Object get(Class<?> clazz, Serializable id) throws DaoException {
		// TODO Auto-generated method stub
		return SessionUtil.get(clazz, id);
	}

	@Override
	public List find(String sql, List<Object> params, Class<?> clazz)
			throws DaoException {
		// TODO Auto-generated method stub
		return EntityUtil.fillObject(SessionUtil.query(sql, params), clazz);
	}

	@Override
	public List<Object[]> find(String sql, List<Object> params) throws DaoException {
		// TODO Auto-generated method stub
		return EntityUtil.fillList(SessionUtil.query(sql, params));
	}

	@Override
	public List find(String catelog, String schema,
			String table,List<ColumnInfo> columns, Expression where, List<Order> orders,
			Integer begin, Integer size,Class<?> clazz) throws DaoException {
		// TODO Auto-generated method stub
		return EntityUtil.fillObject(SessionUtil.query(columns, catelog, schema, table, where, null, null, orders, begin, size), clazz);
	}

	@Override
	public List<Object[]> find(String catelog, String schema,
			String table,List<ColumnInfo> columns, Expression where, List<ColumnInfo> groups, Expression having,
			List<Order> orders, Integer begin, Integer size) throws DaoException {
		// TODO Auto-generated method stub
		return EntityUtil.fillList(SessionUtil.query(columns, catelog, schema, table, where, groups, having, orders, begin, size));
	}
	
}
