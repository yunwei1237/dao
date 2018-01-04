package org.tcf.sql.entity.exp;

import java.util.List;

import org.tcf.sql.entity.Operation;
import org.tcf.sql.entity.SqlInfo;
import org.tcf.sql.util.CollectionUtil;
import org.tcf.sql.util.StringUtil;

/**
 * 代表一个值的表达式
 * 如：age = 12
 * @author Archer Tan
 *
 */
public class ExpValue implements Expression {
	private String name;
	private Object value;
	private Operation operation;
	public ExpValue() {
	}
	public ExpValue(String name, Object value, Operation operation) {
		super();
		this.name = name;
		this.value = value;
		this.operation = operation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	/**
	 * 获得表达式的sql形式
	 * @return
	 */
	public SqlInfo get() {
		//关系表达式
		if(Operation.in != operation && CollectionUtil.isCollection(value))
			throw new RuntimeException(String.format("%s操作符不能是集合", operation));
		String op = "";
		if(Operation.eq == operation){
			op = "=";
		}else if(Operation.ne == operation){
			op = "!=";
		}
		else if(Operation.gt == operation){
			op = ">";
		}
		else if(Operation.lt == operation){
			op = "<";
		}
		else if(Operation.ge == operation){
			op = ">=";
		}
		else if(Operation.le == operation){
			op = "<=";
		}else if(Operation.in == operation){
			op = "in";
		}else if(Operation.like == operation){
			op = "like";
		}
		Object val = Operation.in == operation ? StringUtil.packing(value, "(", ")", ","):value;
		String sql = String.format("%s %s ?", name,op);
		return new SqlInfo(sql, name, val);
	}
	@Override
	public String toString() {
		return "ExpValue [name=" + name + ", value=" + value + ", operation="
				+ operation + "]";
	}
	
}
