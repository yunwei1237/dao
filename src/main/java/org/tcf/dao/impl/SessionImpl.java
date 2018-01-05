package org.tcf.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import org.tcf.dao.Session;
import org.tcf.exception.DaoException;
import org.tcf.sql.entity.ColumnInfo;
import org.tcf.sql.entity.NonQueryType;
import org.tcf.sql.entity.Order;
import org.tcf.sql.entity.exp.Expression;
import org.tcf.sql.generator.SqlGenerator;
import org.tcf.sql.util.EntityUtil;
import org.tcf.sql.util.SessionUtil;


public class SessionImpl implements Session {
	private SessionUtil sessionUtil;
	public SessionImpl(SqlGenerator gen, Connection conn) {
		super();
		this.sessionUtil = new SessionUtil(gen, conn);
	}

	@Override
	public int save(Object obj){
		// TODO Auto-generated method stub
		return sessionUtil.update(obj,NonQueryType.insert);
	}

	@Override
	public int update(Object obj){
		// TODO Auto-generated method stub
		return sessionUtil.update(obj,NonQueryType.update);
	}

	@Override
	public int delete(Object obj){
		// TODO Auto-generated method stub
		return sessionUtil.update(obj,NonQueryType.delete);
	}

	@Override
	public Object get(Class<?> clazz, Serializable id) throws DaoException {
		// TODO Auto-generated method stub
		return sessionUtil.get(clazz, id);
	}

	@Override
	public List find(String sql, List<Object> params, Class<?> clazz)
			throws DaoException {
		// TODO Auto-generated method stub
		return EntityUtil.fillObject(sessionUtil.query(sql, params), clazz);
	}

	@Override
	public List<Object[]> find(String sql, List<Object> params) throws DaoException {
		// TODO Auto-generated method stub
		return EntityUtil.fillList(sessionUtil.query(sql, params));
	}

	@Override
	public List find(String catelog, String schema,
			String table,List<ColumnInfo> columns, Expression where, List<Order> orders,
			Integer begin, Integer size,Class<?> clazz) throws DaoException {
		// TODO Auto-generated method stub
		return EntityUtil.fillObject(sessionUtil.query(columns, catelog, schema, table, where, null, null, orders, begin, size), clazz);
	}

	@Override
	public List<Object[]> find(String catelog, String schema,
			String table,List<ColumnInfo> columns, Expression where, List<ColumnInfo> groups, Expression having,
			List<Order> orders, Integer begin, Integer size) throws DaoException {
		// TODO Auto-generated method stub
		return EntityUtil.fillList(sessionUtil.query(columns, catelog, schema, table, where, groups, having, orders, begin, size));
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		sessionUtil.commit();
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub
		sessionUtil.rollback();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		sessionUtil.close();
	}
	
}
