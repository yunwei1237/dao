package org.tcf.sql.entity.exp;

import java.util.List;
import java.util.Map;

import org.tcf.sql.entity.ConditionType;
import org.tcf.sql.entity.SqlInfo;
import org.tcf.sql.util.CollectionUtil;

/**
 * 代表逻辑表达式
 * and ,or,not
 * @author Archer Tan
 *
 */
public class ExpLogic implements Expression {
	private Expression exp1;
	private Expression exp2;
	private ConditionType type;
	private ExpLogic() {
		super();
		// TODO Auto-generated constructor stub
	}
	private ExpLogic(Expression exp1, Expression exp2, ConditionType type) {
		super();
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.type = type;
	}
	public static ExpLogic and(Expression exp1, Expression exp2){
		return new ExpLogic(exp1, exp2, ConditionType.and);
	}
	public static ExpLogic or(Expression exp1, Expression exp2){
		return new ExpLogic(exp1, exp2, ConditionType.or);
	}
	public static ExpLogic not(Expression exp1){
		return new ExpLogic(exp1,null, ConditionType.not);
	}
	public Expression getExp1() {
		return exp1;
	}
	public void setExp1(Expression exp1) {
		this.exp1 = exp1;
	}
	public Expression getExp2() {
		return exp2;
	}
	public void setExp2(Expression exp2) {
		this.exp2 = exp2;
	}
	public ConditionType getType() {
		return type;
	}
	public void setType(ConditionType type) {
		this.type = type;
	}
	@Override
	public SqlInfo get() {
		// TODO Auto-generated method stub
		String sql = null;
		SqlInfo info = new SqlInfo();
		if(getType() == null)
			throw new RuntimeException(String.format("逻辑操作符不能为null"));
		if(getType() == ConditionType.not){
			if(exp2 != null){
				throw new RuntimeException(String.format("%s逻辑操作符只能修饰一个表达式", getType()));
			}
			if(exp1 instanceof ExpValue){
				sql = String.format("%s %s", getType(),exp1.get().getSql());
				info.addPlaceholders(exp1.get().getKeyList(), exp1.get().getValues());
			}
			else{
				sql = String.format("%s(%s)", getType(),exp1.get().getSql());
				info.addPlaceholders(exp1.get().getKeyList(), exp1.get().getValues());
			}
		}else{
			sql = String.format("%s %s %s", exp1.get().getSql(),getType(),exp2.get().getSql());
			info.addPlaceholders(exp1.get().getKeyList(), exp1.get().getValues());
			info.addPlaceholders(exp2.get().getKeyList(), exp2.get().getValues());
		}
		info.setSql(sql);
		return info;
	}
}
