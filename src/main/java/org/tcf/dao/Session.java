package org.tcf.dao;

import java.util.List;

import org.tcf.sql.entity.Order;


/**
 * 
 * @author Archer Tan
 *
 */
public interface Session {
	int save(Object obj) throws Exception;
	int update(Object obj) throws Exception;
	int delete(Object obj) throws Exception;
	List find(String sql,Class clazz,Object ...params);
	List find(Object Object);
	List find(Object Object,Order...orders);
	List find(Object Object,String[] likes);
	List find(Object Object,String[] likes,Order...orders);
	List find(Object Object,String[] likes,Integer begin,Integer size);
	List find(Object Object,String[] likes,Integer begin,Integer size,Order...orders);
	List findByAndAll(Object Object,String[] likes,String[] groups,Order[] orders,Integer begin,Integer size);
	List findByOrAll(Object Object,String[] likes,String[] groups,Order[] orders,Integer begin,Integer size);
}
