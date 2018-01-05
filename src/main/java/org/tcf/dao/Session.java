package org.tcf.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.tcf.exception.DaoException;
import org.tcf.sql.entity.ColumnInfo;
import org.tcf.sql.entity.Order;
import org.tcf.sql.entity.exp.Expression;

/**
 * 
 * @author Archer Tan
 *
 */
public interface Session {
	int save(Object obj) throws DaoException;
	int update(Object obj) throws DaoException;
	int delete(Object obj) throws DaoException;
	void commit() throws DaoException;
	void rollback() throws DaoException;
	void close() throws DaoException;
	Object get(Class<?> clazz,Serializable id) throws DaoException;
	List findByExample(Object entity);
	List findByExample(Object entity,Order...orders);
	List findByExample(Object entity,Integer begin,Integer size);
	List findByExample(Object entity,Integer begin,Integer size,Order...orders);
	List find(String sql,List<Object> params,Class clazz) throws DaoException;
	List find(String sql,List<Object> params) throws DaoException;
	List find(String catelog,String schema,String table,List<ColumnInfo> columns,Expression where,List<Order> orders,Integer begin,Integer size,Class clazz) throws DaoException;
	List find(String catelog,String schema,String table,List<ColumnInfo> columns,Expression where,List<ColumnInfo> groups,Expression having,List<Order> orders,Integer begin,Integer size) throws DaoException;
}
