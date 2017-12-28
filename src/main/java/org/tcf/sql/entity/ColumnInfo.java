package org.tcf.sql.entity;

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
	private Boolean primaryKey;
	/**
	 * 是否是外键
	 */
	private Boolean foreignKey;
	public ColumnInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ColumnInfo(String name, Class type, int length, int scale,
			Boolean notNull, Object value, Boolean primaryKey,
			Boolean foreignKey) {
		super();
		this.name = name;
		this.type = type;
		this.length = length;
		this.scale = scale;
		this.notNull = notNull;
		this.value = value;
		this.primaryKey = primaryKey;
		this.foreignKey = foreignKey;
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
	@Override
	public String toString() {
		return "ColumnInfo [name=" + name + ", type=" + type + ", length="
				+ length + ", scale=" + scale + ", notNull=" + notNull
				+ ", value=" + value + ", primaryKey=" + primaryKey
				+ ", foreignKey=" + foreignKey + "]";
	}
}
