package org.tcf.sql.entity;

/**
 * where子句中的表达式操作符
 * 如：=,!=(<>),>,<,>=,<=,in,like
 * @author Archer Tan
 *
 */
public enum Operation {
	/**
	 * =
	 */
	eq,
	/**
	 * !=
	 */
	ne,
	/**
	 * >
	 */
	gt,
	/**
	 * <
	 */
	lt,
	/**
	 * >=
	 */
	ge,
	/**
	 * <=
	 */
	le,
	/**
	 * in
	 */
	in,
	/**
	 * like
	 */
	like
}
