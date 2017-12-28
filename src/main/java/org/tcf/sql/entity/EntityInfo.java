package org.tcf.sql.entity;

import java.util.List;

import org.tcf.annotation.PrimaryKeyType;


/**
 * 用于保存实体的信息，以便于在生成sql语句时使用。
 * @author Archer Tan
 *
 */
public class EntityInfo {
	/**
	 * 用于保存数据库名
	 */
	private String catelog;
	/**
	 * 用于保存表名
	 */
	private String table;
	/**
	 * 用于保存主键的类型
	 */
	private PrimaryKeyType type;
	/**
	 * 用于保存某些主键下的其它的信息，比如序列名称
	 */
	private String typeValue;
	/**
	 * 用于保存所有列信息
	 */
	private List<ColumnInfo> columns;
	public EntityInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EntityInfo(String catelog, String table, PrimaryKeyType type,
			String typeValue, List<ColumnInfo> columns) {
		super();
		this.catelog = catelog;
		this.table = table;
		this.type = type;
		this.typeValue = typeValue;
		this.columns = columns;
	}
	public String getCatelog() {
		return catelog;
	}
	public void setCatelog(String catelog) {
		this.catelog = catelog;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public PrimaryKeyType getType() {
		return type;
	}
	public void setType(PrimaryKeyType type) {
		this.type = type;
	}
	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	public List<ColumnInfo> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnInfo> columns) {
		this.columns = columns;
	}
	@Override
	public String toString() {
		return "EntityInfo [catelog=" + catelog + ", table=" + table
				+ ", type=" + type + ", typeValue=" + typeValue + ", columns="
				+ columns + "]";
	}
}
