package org.tcf.sql.entity.exp;

import org.tcf.sql.entity.SqlInfo;

public class ExpParentheses implements Expression {
	private Expression exp;
	public ExpParentheses(Expression exp) {
		this.exp = exp;
	}
	public Expression getExp() {
		return exp;
	}
	public void setExp(Expression exp) {
		this.exp = exp;
	}
	@Override
	public SqlInfo get() {
		// TODO Auto-generated method stub
		return new SqlInfo(String.format("(%s)", exp.get().getSql()), exp.get().getKeyList(),exp.get().getValues());
	}
}
