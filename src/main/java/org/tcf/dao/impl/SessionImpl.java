package org.tcf.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.tcf.annotation.PrimaryKeyType;
import org.tcf.dao.BaseDao;
import org.tcf.dao.Session;
import org.tcf.sql.entity.ConditionType;
import org.tcf.sql.entity.EntityInfo;
import org.tcf.sql.entity.Order;
import org.tcf.sql.generator.SqlGenerator;
import org.tcf.sql.generator.impl.MySqlSqlGeneratorImpl;
import org.tcf.sql.util.EntityUtil;


public class SessionImpl implements Session {
	Logger log = Logger.getLogger(SessionImpl.class);
	/**
	 * 该对象不应该直接创建而是应该通过配置来完成，以便于切换 成其它的数据库(oracle)
	 */
	private SqlGenerator gen = new MySqlSqlGeneratorImpl();
	@Override
	public int save(Object obj) throws Exception {
		// TODO Auto-generated method stub
		EntityInfo info = EntityUtil.getInfo(obj);
		String sql = gen.genertInsert(info);
		Object[] params = getParams(info,"insert");
		log.info("dao:"+sql);
		log.info("dao:"+Arrays.toString(params));
		return BaseDao.executeNonQuery(sql, params);
	}
	public Object[] getParams(EntityInfo info,String type){
		List list = new ArrayList();
		if("insert".equals(type)){
			if(info.getType() == PrimaryKeyType.assigned){
				list.add(info.getIdVal());
			}
			list.addAll(info.getValues());
		}else if("update".equals(type) ){
			list.addAll(info.getValues());
			list.add(info.getIdVal());
		}else if("delete".equals(type)){
			list.add(info.getIdVal());
		}else if("select".equals(type)){
			if(info.getIdVal() != null){
				list.add(info.getIdVal());
			}
			list.addAll(info.getValues());
		}
		return list.toArray();
	}
	@Override
	public int update(Object obj) throws Exception {
		// TODO Auto-generated method stub
		EntityInfo info = EntityUtil.getInfo(obj);
		String sql = gen.genertUpdate(info);
		Object[] params = getParams(info,"update");
		log.info("dao:"+sql);
		log.info("dao:"+Arrays.toString(params));
		return BaseDao.executeNonQuery(sql, params);
	}

	@Override
	public int delete(Object obj) throws Exception {
		// TODO Auto-generated method stub
		EntityInfo info = EntityUtil.getInfo(obj);
		String sql = gen.genertDelete(info);
		Object[] params = getParams(info,"delete");
		log.info("dao:"+sql);
		log.info("dao:"+Arrays.toString(params));
		return BaseDao.executeNonQuery(sql, params);
	}

	@Override
	public List find(Object obj) {
		// TODO Auto-generated method stub
		return this.findByAndAll(obj, null, null, null, null, null);
	}

	@Override
	public List find(Object obj, Order... orders) {
		// TODO Auto-generated method stub
		return this.findByAndAll(obj, null, null, orders, null, null);
	}

	@Override
	public List find(Object obj, String[] likes) {
		// TODO Auto-generated method stub
		return this.findByAndAll(obj, likes, null, null, null, null);
	}

	@Override
	public List find(Object obj, String[] likes, Order... orders) {
		// TODO Auto-generated method stub
		return this.findByAndAll(obj, likes, null, orders, null, null);
	}

	@Override
	public List find(Object obj, String[] likes, Integer begin,
			Integer size) {
		// TODO Auto-generated method stub
		return this.findByAndAll(obj, likes, null, null, begin, size);
	}

	@Override
	public List find(Object obj, String[] likes, Integer begin,
			Integer size, Order... orders) {
		// TODO Auto-generated method stub
		return this.findByAndAll(obj, likes, null, orders, begin, size);
	}
	@Override
	public List findByAndAll(Object obj, String[] likes,
			String[] groups, Order[] orders, Integer begin, Integer size) {
		// TODO Auto-generated method stub
		EntityInfo info;
		List list = new ArrayList();
		try {
			info = EntityUtil.getInfo(obj);
			String sql = gen.genertSelect(info, ConditionType.and, likes, groups, orders, begin, size);
			Object[] params = getParams(info, "select");
			log.info("dao:"+sql);
			log.info("dao:"+Arrays.toString(params));
			ResultSet rs = BaseDao.executeQuery(sql, params);
			list = EntityUtil.fill(rs, obj.getClass());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List findByOrAll(Object obj, String[] likes,
			String[] groups, Order[] orders, Integer begin, Integer size) {
		// TODO Auto-generated method stub
		EntityInfo info;
		List list = new ArrayList();
		try {
			info = EntityUtil.getInfo(obj);
			String sql = gen.genertSelect(info, ConditionType.or, likes, groups, orders, begin, size);
			Object[] params = getParams(info, "select");
			log.info("dao:"+sql);
			log.info("dao:"+Arrays.toString(params));
			ResultSet rs = BaseDao.executeQuery(sql, params);
			list = EntityUtil.fill(rs, obj.getClass());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List find(String sql,Class clazz,Object... params) {
		// TODO Auto-generated method stub
		return EntityUtil.fill(BaseDao.executeQuery(sql, params), clazz);
	}

}
