package org.tcf.sql.entity.exp;

import org.tcf.sql.entity.SqlInfo;


/**
 * 代表一个表达式
 * @author Archer Tan
 *
 */
public interface Expression {
	SqlInfo get() throws RuntimeException;
}
