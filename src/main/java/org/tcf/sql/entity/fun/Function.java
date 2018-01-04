package org.tcf.sql.entity.fun;

import org.tcf.sql.util.StringUtil;

public class Function {
	private String funName;
	public String get(String... params) {
		return String.format("%s(%s)", funName,StringUtil.packing(params, "", "", ","));
	}
	public Function(String funName) {
		super();
		this.funName = funName;
	}
	public String getFunName() {
		return funName;
	}
	public void setFunName(String funName) {
		this.funName = funName;
	}
	@Override
	public String toString() {
		return "Function [funName=" + funName + "]";
	}
}
