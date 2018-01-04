package org.tcf.dao;

import java.util.List;

import org.tcf.sql.entity.ConditionType;
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
	int update(Object obj)  throws DaoException;
	int delete(Object obj)  throws DaoException;
	List find(String sql,Class clazz,Object ...params)  throws DaoException;
	List find(Object obj);
	List find(Object obj,Order...orders);
	List find(Object obj,Integer begin,Integer size);
	List find(Object obj,Integer begin,Integer size,Order...orders);
	List find(Object obj,String[] likes,String[] groups,Order[] orders,Integer begin,Integer size,ConditionType type);
	List find(Class clazz,Expression where,String[] groups,Expression having,Order[] orders,Integer begin,Integer size)  throws DaoException;
}
