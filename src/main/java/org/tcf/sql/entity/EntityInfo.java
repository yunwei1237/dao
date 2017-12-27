package org.tcf.sql.entity;

import java.util.List;

import org.tcf.annotation.PrimaryKeyType;


/**
 * 用于保存实体的信息，以便于在生成sql语句时使用。
 * @author Archer Tan
 *
 */
public class EntityInfo {
	private String catelog;
	private String table;
	private String id;
	private Object idVal;
	private PrimaryKeyType type;
	private List<String> columns;
	private List<Object> values;
	
	public EntityInfo() {
		super();
	}
	public EntityInfo(String catelog, String table, String id, Object idVal,
			PrimaryKeyType type, List<String> columns, List<Object> values) {
		super();
		this.catelog = catelog;
		this.table = table;
		this.id = id;
		this.idVal = idVal;
		this.type = type;
		this.columns = columns;
		this.values = values;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Object getIdVal() {
		return idVal;
	}
	public void setIdVal(Object idVal) {
		this.idVal = idVal;
	}
	public PrimaryKeyType getType() {
		return type;
	}
	public void setType(PrimaryKeyType type) {
		this.type = type;
	}
	public List<String> getColumns() {
		return columns;
	}
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	public List<Object> getValues() {
		return values;
	}
	public void setValues(List<Object> values) {
		this.values = values;
	}
	@Override
	public String toString() {
		return "EntityInfo [catelog=" + catelog + ", table=" + table + ", id="
				+ id + ", idVal=" + idVal + ", type=" + type + ", columns="
				+ columns + ", values=" + values + "]";
	}
}
