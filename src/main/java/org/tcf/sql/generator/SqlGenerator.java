package org.tcf.sql.generator;

import org.tcf.sql.entity.EntityInfo;
import org.tcf.sql.entity.Order;
import org.tcf.sql.entity.SqlInfo;
import org.tcf.sql.entity.exp.Expression;
/**
 * 
 * 用于生成sql语句
 * @author Archer Tan
 *
 */
public interface SqlGenerator {
	/**
	 * 生成insert语句
	 * @param catolog 数据库名
	 * @param schema 模式名
	 * @param table 表名
	 * @param columns 列名集合
	 * @return
	 * @throws Exception
	 */
	SqlInfo genertInsert(EntityInfo info);
	/**
	 * 生成update语句
	 * @param catolog 数据库名
	 * @param schema 模式名
	 * @param table 表名
	 * @param columns 列名集合
	 * @param where 条件
	 * @return
	 * @throws Exception
	 */
	SqlInfo genertUpdate(EntityInfo info,Expression where);
	/**
	 * 生成delete语句
	 * @param catolog 数据库名
	 * @param schema 模式名
	 * @param table 表名
	 * @param where 条件
	 * @return
	 * @throws Exception
	 */
	SqlInfo genertDelete(EntityInfo info,Expression where);
	/**
	 * 生成select语句
	 * @param catolog 数据库名
	 * @param schema 模式名
	 * @param table 表名
	 * @param selects 选择的字段集合
	 * @param where 条件
	 * @param groups 分组
	 * @param having 分组条件
	 * @param orders 排序
	 * @param begin 起始的行
	 * @param size 最多的行数
	 * @return
	 * @throws Exception
	 */
	SqlInfo genertSelect(EntityInfo info,Expression where,String[] groups,Expression having,Order[] orders,Integer begin,Integer size);
}
