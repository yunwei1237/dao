package org.tcf.sql.generator;

import java.util.List;

import org.tcf.annotation.PrimaryKeyType;
import org.tcf.sql.entity.ColumnInfo;
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
	 * @param catelog 数据库名
	 * @param schema 模式名
	 * @param table 表名
	 * @param columnInfos 列名集合
	 * @return
	 * @throws Exception
	 */
	SqlInfo genertInsert(String catelog,String schema,String table,List<ColumnInfo> columnInfos,PrimaryKeyType type);
	/**
	 * 生成update语句
	 * @param catolog 数据库名
	 * @param schema 模式名
	 * @param table 表名
	 * @param columnInfos 列名集合
	 * @param where 条件
	 * @return
	 * @throws Exception
	 */
	SqlInfo genertUpdate(String catelog,String schema,String table,List<ColumnInfo> columnInfos,Expression where);
	/**
	 * 生成delete语句
	 * @param catolog 数据库名
	 * @param schema 模式名
	 * @param table 表名
	 * @param where 条件
	 * @return
	 * @throws Exception
	 */
	SqlInfo genertDelete(String catelog,String schema,String table,Expression where);
	/**
	 * 生成select语句
	 * @param catolog 数据库名
	 * @param schema 模式名
	 * @param table 表名
	 * @param columnInfos 选择的字段集合
	 * @param where 条件
	 * @param groups 分组
	 * @param having 分组条件
	 * @param orders 排序
	 * @param begin 起始的行
	 * @param size 最多的行数
	 * @return
	 * @throws Exception
	 */
	SqlInfo genertSelect(String catelog,String schema,String table,List<ColumnInfo> columnInfos,Expression where,List<ColumnInfo> groups,Expression having,List<Order> orders,Integer begin,Integer size);
}
