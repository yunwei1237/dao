package org.tcf.sql.entity;

import org.tcf.sql.entity.fun.Function;

public class ColumnInfo {
	/**
	 * 列名
	 */
	private String name;
	/**
	 * 列类型
	 */
	private Class type;
	/**
	 * 列长度（字符和数字）
	 */
	private int length;
	/**
	 * 保留小数位数（数字）
	 */
	private int scale;
	/**
	 * 是否不为null
	 */
	private Boolean notNull;
	/**
	 * 该字段对应的值
	 */
	private Object value;
	/**
	 * 是否是主键
	 */
	private Boolean primaryKey = false;
	/**
	 * 是否是外键
	 */
	private Boolean foreignKey = false;
	/**
	 * 对该列使用的函数
	 */
	private Function function;
	public ColumnInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ColumnInfo(String name, Class type, int length, int scale,
			Boolean notNull, Object value, Boolean primaryKey,
			Boolean foreignKey, Function function) {
		super();
		this.name = name;
		this.type = type;
		this.length = length;
		this.scale = scale;
		this.notNull = notNull;
		this.value = value;
		this.primaryKey = primaryKey;
		this.foreignKey = foreignKey;
		this.function = function;
	}
	
	public ColumnInfo(String name) {
		super();
		this.name = name;
	}
	public ColumnInfo(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}
	public ColumnInfo(String name, Function function) {
		super();
		this.name = name;
		this.function = function;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Class getType() {
		return type;
	}
	public void setType(Class type) {
		this.type = type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public Boolean getNotNull() {
		return notNull;
	}
	public void setNotNull(Boolean notNull) {
		this.notNull = notNull;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Boolean getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	public Boolean getForeignKey() {
		return foreignKey;
	}
	public void setForeignKey(Boolean foreignKey) {
		this.foreignKey = foreignKey;
	}
	public Function getFunction() {
		return function;
	}
	public void setFunction(Function function) {
		this.function = function;
	}
	/**
	 * 获得列的字符串形式，并检查该列是否有函数修饰，如果有，就显示出来<br />
	 * 有函数，如：avg(age)<br />
	 * 无函数，如：age <br />
	 * @param column
	 * @return
	 */
	public String getColumnString(){
		if(this.getFunction() != null){//如果该列存在一个函数，就使用函数，如：avg(age)
			return this.getFunction().get(this.getName());
		}else{//没有函数，如：age,
			return this.getName();
		}
	}
	@Override
	public String toString() {
		return "ColumnInfo [name=" + name + ", type=" + type + ", length="
				+ length + ", scale=" + scale + ", notNull=" + notNull
				+ ", value=" + value + ", primaryKey=" + primaryKey
				+ ", foreignKey=" + foreignKey + "]";
	}
}
