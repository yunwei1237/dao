package org.tcf.dao;

import java.io.Serializable;
import java.util.List;

import org.tcf.sql.entity.ColumnInfo;
import org.tcf.sql.entity.Order;
import org.tcf.sql.entity.exp.Expression;

import com.tcf.exception.DaoException;


/**
 * 
 * @author Archer Tan
 *
 */
public interface Session {
	int save(Object obj) throws DaoException;
	int update(Object obj) throws DaoException;
	int delete(Object obj) throws DaoException;
	Object get(Class<?> clazz,Serializable id) throws DaoException;
	List find(String sql,List<Object> params,Class<?> clazz) throws DaoException;
	List<Object[]> find(String sql,List<Object> params) throws DaoException;
	List find(String catelog,String schema,String table,List<ColumnInfo> columns,Expression where,List<Order> orders,Integer begin,Integer size,Class<?> clazz) throws DaoException;
	List<Object[]> find(String catelog,String schema,String table,List<ColumnInfo> columns,Expression where,List<ColumnInfo> groups,Expression having,List<Order> orders,Integer begin,Integer size) throws DaoException;
}
