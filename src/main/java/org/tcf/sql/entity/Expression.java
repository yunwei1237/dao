package org.tcf.sql.entity;

import java.util.Collection;

import org.tcf.sql.util.CollectionUtil;
import org.tcf.sql.util.StringUtil;

public class Expression {
	private String name;
	private Object value;
	private Operation operation;
	public Expression() {
	}
	public Expression(String name, Object value, Operation operation) {
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
	 * @throws Exception
	 */
	public String get() throws Exception {
		if(Operation.in != operation && CollectionUtil.isCollection(value))
			throw new Exception(String.format("%s操作符不能是集合", operation));
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
		Object val = Operation.in == operation ?StringUtil.packing(value, "(", ")", ","):value;
		return String.format("%s %s %s", name,op,val);
	}
	@Override
	public String toString() {
		return "Expression [name=" + name + ", value=" + value + ", operation="
				+ operation + "]";
	}
	
}
