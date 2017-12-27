package org.tcf.annotation;

public enum PrimaryKeyType {
	/**
	 * 手动指定主键
	 */
	assigned,
	/**
	 * 使用自动增长，适用于MySql
	 */
	autoIncrement,
	/**
	 * 使用序列，适用于oracle
	 */
	sequence
}
