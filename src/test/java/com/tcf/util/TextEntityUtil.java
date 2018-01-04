package com.tcf.util;

import org.junit.Test;
import org.tcf.sql.entity.EntityInfo;
import org.tcf.sql.entity.exp.Expression;
import org.tcf.sql.util.EntityUtil;

import com.tcf.entity.Grade;

public class TextEntityUtil {

	@Test
	public void test1() throws Exception{
		Grade grade = new Grade();
		grade.setGradeId(2);
		grade.setGradeName("ab");
		EntityInfo info = EntityUtil.getInfo(grade,false);
		Expression exp = EntityUtil.getExpressionByAnd(info.getColumns(), new String[]{"gradeid"});
		System.out.println(exp.get());
	}
}
