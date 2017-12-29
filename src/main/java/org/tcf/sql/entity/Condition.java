package org.tcf.sql.entity;



public class Condition {
	private Expression express;
	private ConditionType conditionType;
	private Condition next;
	private Condition() {
		super();
	}
	private Condition(Expression exp) {
		super();
		this.express = exp;
	}
	public static Condition getInstance(Expression exp){
			return new Condition(exp);
	}
	public Condition and(Expression exp){
		return add(exp, ConditionType.and);
	}
	public Condition or(Expression exp){
		return add(exp, ConditionType.or);
	}
	public Condition not(){
		return add(null, ConditionType.not);
	}
	public Condition add(Expression exp,ConditionType conditionType){
		this.conditionType = conditionType;
		if(exp != null)
			next = new Condition(exp);
		return next;
	}
	public Expression getExpress() {
		return express;
	}
	public void setExpress(Expression express) {
		this.express = express;
	}
	public ConditionType getConditionType() {
		return conditionType;
	}
	public void setConditionType(ConditionType conditionType) {
		this.conditionType = conditionType;
	}
	public Condition getNext() {
		return next;
	}
	public void setNext(Condition next) {
		this.next = next;
	}
	
	
}
