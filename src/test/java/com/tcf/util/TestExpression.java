package com.tcf.util;

import org.junit.Test;
import org.tcf.sql.entity.Expression;
import org.tcf.sql.entity.Operation;

public class TestExpression {
	
	@Test
	public void testExpression(){
		//System.out.println(new Expression("age", "123", Operation.eq));
		try {
			System.out.println(new Expression("age", new String[]{"abc","123","hqhq"}, Operation.eq).get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
