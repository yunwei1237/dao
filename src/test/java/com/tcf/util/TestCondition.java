package com.tcf.util;

import org.junit.Test;
import org.tcf.sql.entity.Condition;
import org.tcf.sql.util.ConditionUtil;
import org.tcf.sql.util.ExpUtil;

public class TestCondition {
	
	@Test
	public void test() throws Exception{
		Condition condition = Condition.getInstance(ExpUtil.eq("name", "archer")).and(ExpUtil.gt("age", 12));
		String str = ConditionUtil.get(condition);
		System.out.println(str);
	}
}
