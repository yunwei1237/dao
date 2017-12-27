package org.tcf.sql.entity;

public class ColumnInfo {
	private String name;
	private Class type;
	private boolean primaryKey;
	private boolean foreignKey;
	
	public ColumnInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ColumnInfo(String name, Class type, boolean primaryKey,
			boolean foreignKey) {
		super();
		this.name = name;
		this.type = type;
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
	public boolean isPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	public boolean isForeignKey() {
		return foreignKey;
	}
	public void setForeignKey(boolean foreignKey) {
		this.foreignKey = foreignKey;
	}
	@Override
	public String toString() {
		return "ColumnInfo [name=" + name + ", type=" + type + ", primaryKey="
				+ primaryKey + ", foreignKey=" + foreignKey + "]";
	}
}
