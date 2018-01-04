package com.tcf.util;

import org.junit.Test;
import org.tcf.sql.entity.Operation;
import org.tcf.sql.entity.exp.ExpLogic;
import org.tcf.sql.entity.exp.ExpValue;
import org.tcf.sql.util.ExpUtil;

public class TestExpression {
	
	@Test
	public void testExpression() throws Exception{
		System.out.println(ExpLogic.not(ExpUtil.eq("age", 12)).get());
		System.out.println(ExpLogic.and(ExpUtil.eq("age", 12),ExpUtil.like("name", "abc")).get());
		System.out.println(ExpLogic.not(ExpLogic.and(ExpUtil.eq("age", 12),ExpUtil.like("name", "abc"))).get());
		System.out.println(ExpUtil.and(ExpUtil.le("age", 12),ExpUtil.eq("name", "aaa"),ExpUtil.in("grade", new int[]{1,3,8})).get());
	}
}
