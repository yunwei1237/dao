package org.tcf.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.tcf.dao.Session;
import org.tcf.sql.entity.ConditionType;
import org.tcf.sql.entity.EntityInfo;
import org.tcf.sql.entity.Order;
import org.tcf.sql.entity.SqlInfo;
import org.tcf.sql.entity.exp.Expression;
import org.tcf.sql.generator.SqlGenerator;
import org.tcf.sql.generator.impl.MySqlSqlGenerator;
import org.tcf.sql.util.EntityUtil;
import org.tcf.sql.util.ExpUtil;
import org.tcf.sql.util.StringUtil;

public class SessionImpl implements Session {
	private static final Logger logger = Logger.getLogger(SessionImpl.class);
	private SqlGenerator gen = new MySqlSqlGenerator();
	@Override
	public int save(Object obj){
		// TODO Auto-generated method stub
		EntityInfo info = EntityUtil.getInfo(obj);
		SqlInfo sqlInfo = gen.genertInsert(info);
		Object[] params = sqlInfo.getValues(EntityUtil.change(info.getColumns()));
		logger.info(String.format("   Sql:\t%s", sqlInfo.getSql()));
		logger.info(String.format("Params:\t%s", StringUtil.getParamsInfo(params)));
		//return BaseDao.executeNonQuery(sql, params);
		return 0;
	}

	@Override
	public int update(Object obj){
		// TODO Auto-generated method stub
		EntityInfo info = EntityUtil.getInfo(obj);
		Expression where = ExpUtil.eq(info.getId().getName(), info.getId().getValue());
		SqlInfo sqlInfo = gen.genertUpdate(info,where);
		Object[] params = sqlInfo.getValues(EntityUtil.change(info.getColumns()));
		logger.info(String.format("   Sql:\t%s", sqlInfo.getSql()));
		logger.info(String.format("Params:\t%s", StringUtil.getParamsInfo(params)));
		//return BaseDao.executeNonQuery(sql, params);
		return 0;
	}

	@Override
	public int delete(Object obj){
		// TODO Auto-generated method stub
		EntityInfo info = EntityUtil.getInfo(obj);
		Expression where = ExpUtil.eq(info.getId().getName(), info.getId().getValue());
		SqlInfo sqlInfo = gen.genertDelete(info,where);
		Object[] params = sqlInfo.getValues(EntityUtil.change(info.getColumns()));
		logger.info(String.format("   Sql:\t%s", sqlInfo.getSql()));
		logger.info(String.format("Params:\t%s", StringUtil.getParamsInfo(params)));
		//return BaseDao.executeNonQuery(sql, params);
		return 0;
	}

	@Override
	public List find(String sql, Class clazz, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(Object obj, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(Object obj, Integer begin, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(Object obj, Integer begin, Integer size,
			Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(Object obj,String[] likes, String[] groups, Order[] orders,
			Integer begin, Integer size, ConditionType type){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(Class clazz, Expression where, String[] groups,
			Expression having, Order[] orders, Integer begin, Integer size){
		// TODO Auto-generated method stub
		EntityInfo info = null;
		try {
			info = EntityUtil.getInfo(clazz.newInstance(),true);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlInfo sqlInfo = gen.genertSelect(info, where, groups, having, orders, begin, size);
		Object[] params = sqlInfo.getValues(EntityUtil.change(info.getColumns()));
		logger.info(String.format("   Sql:\t%s", sqlInfo.getSql()));
		logger.info(String.format("Params:\t%s", StringUtil.getParamsInfo(params)));
		//ResultSet rs = BaseDao.executeQuery(sqlInfo.getSql(), params);
		//return EntityUtil.fill(rs, clazz);
		return null;
	}
}
