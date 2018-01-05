package org.tcf.sql.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.tcf.dao.BaseDao;
import org.tcf.exception.DaoException;
import org.tcf.sql.entity.ColumnInfo;
import org.tcf.sql.entity.EntityInfo;
import org.tcf.sql.entity.NonQueryType;
import org.tcf.sql.entity.Order;
import org.tcf.sql.entity.SqlInfo;
import org.tcf.sql.entity.exp.Expression;
import org.tcf.sql.generator.SqlGenerator;

public class SessionUtil {
	private static final Logger logger = Logger.getLogger(SessionUtil.class);
	private SqlGenerator gen;
	private BaseDao basedao;
	
	public SessionUtil(SqlGenerator gen, Connection conn) {
		super();
		this.gen = gen;
		this.basedao = new BaseDao(conn);
	}
	/**
	 * 执行查询
	 * @param columns
	 * @param catelog
	 * @param schema
	 * @param table
	 * @param where
	 * @param groups
	 * @param having
	 * @param orders
	 * @param begin
	 * @param size
	 * @return
	 */
	public ResultSet query(List<ColumnInfo> columns, String catelog, String schema,
			String table, Expression where, List<ColumnInfo> groups, Expression having,
			List<Order> orders, Integer begin, Integer size){
		SqlInfo sqlInfo = gen.genertSelect(catelog, schema, table, columns, where, groups, having, orders, begin, size);
		Object[] params = sqlInfo.getValues(EntityUtil.change(columns));
		logger.info(String.format("   Sql:\t%s", sqlInfo.getSql()));
		logger.info(String.format("Params:\t%s", StringUtil.getParamsInfo(params)));
		return basedao.executeQuery(sqlInfo.getSql(), params);
	}
	/**
	 * 根据sql语句查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public ResultSet query(String sql, List<Object> params){
		logger.info(String.format("   Sql:\t%s", sql));
		logger.info(String.format("Params:\t%s", StringUtil.getParamsInfo(params.toArray())));
		return basedao.executeQuery(sql, params.toArray());
	}
	/**
	 * 执行增删改
	 * @param obj
	 * @param type
	 * @return
	 */
	public int update(Object obj,NonQueryType type){
		EntityInfo info = EntityUtil.getInfo(obj);
		Expression where = ExpUtil.eq(info.getId().getName(), info.getId().getValue());
		SqlInfo sqlInfo = null;
		if(type == NonQueryType.insert){
			sqlInfo = gen.genertInsert(info.getCatelog(), info.getSchema(), info.getTable(), info.getColumns(), info.getType());
		}else if(type == NonQueryType.update){
			sqlInfo = gen.genertUpdate(info.getCatelog(), info.getSchema(), info.getTable(), info.getColumns(),where);
		}else if(type == NonQueryType.delete){
			sqlInfo = gen.genertDelete(info.getCatelog(), info.getSchema(), info.getTable(), where);
		}
		Object[] params = sqlInfo.getValues(EntityUtil.change(info.getColumns()));
		logger.info(String.format("   Sql:\t%s", sqlInfo.getSql()));
		logger.info(String.format("Params:\t%s", StringUtil.getParamsInfo(params)));
		return basedao.executeNonQuery(sqlInfo.getSql(), params);
	}
	/**
	 * 根据id查询对象
	 * @param clazz
	 * @param id
	 * @return 如果查询不到返回null
	 */
	public Object get(Class clazz,Serializable id){
		Object obj = null;
		try {
			EntityInfo info = EntityUtil.getInfo(clazz.newInstance(),true);//查询全部的列
			Expression where = ExpUtil.eq(info.getId().getName(), id);
			ResultSet rs = query(null, info.getCatelog(), info.getSchema(), info.getTable(), where, null, null, null, null, null);
			List list = EntityUtil.fillObject(rs, clazz);
			if(list.size()> 0)
				return list.get(0);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	public void commit() {
		// TODO Auto-generated method stub
		try {
			basedao.getConn().commit();
			logger.info("transaction:commited!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}
	}

	public void rollback() {
		// TODO Auto-generated method stub
		try {
			basedao.getConn().rollback();
			logger.info("transaction:rollbacked!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}
	}

	public void close() {
		// TODO Auto-generated method stub
		try {
			basedao.getConn().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}
	}
}
