package org.tcf.sql.generator;

import org.tcf.sql.entity.ConditionType;
import org.tcf.sql.entity.EntityInfo;
import org.tcf.sql.entity.Order;
/**
 * 
 * 用于生成sql语句
 * @author Archer Tan
 *
 */
public interface SqlGenerator {
	/**
	 * 生成insert语句
	 * @param info 生成sql需要的字段信息和数据库信息
	 * @return sql语句
	 * @throws Exception
	 */
	String genertInsert(EntityInfo info) throws Exception;
	/**
	 * 生成update语句
	 * @param info 生成sql需要的字段信息和数据库信息
	 * @return sql语句
	 * @throws Exception
	 */
	String genertUpdate(EntityInfo info) throws Exception;
	/**
	 * 生成delete语句
	 * @param info 生成sql需要的字段信息和数据库信息
	 * @return sql语句
	 * @throws Exception
	 */
	String genertDelete(EntityInfo info) throws Exception;
	/**
	 * 生成select语句
	 * @param info 生成sql需要的字段信息和数据库信息
	 * @param type 生成的条件之间的逻辑关系
	 * @param likes 指定哪一些字段需要模糊查询
	 * @param groups 使用哪一些字段进行分组
	 * @param orders 排序
	 * @param begin 起始的行
	 * @param size 最多的行数
	 * @return
	 * @throws Exception
	 */
	String genertSelect(EntityInfo info,ConditionType type,String[] likes,String[] groups,Order[] orders,Integer begin,Integer size) throws Exception;
}
