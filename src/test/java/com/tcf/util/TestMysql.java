package com.tcf.util;

import org.junit.Test;
import org.tcf.sql.entity.ColumnInfo;
import org.tcf.sql.entity.EntityInfo;
import org.tcf.sql.entity.Order;
import org.tcf.sql.entity.OrderType;
import org.tcf.sql.generator.impl.MySqlSqlGenerator;
import org.tcf.sql.util.EntityUtil;
import org.tcf.sql.util.ExpUtil;

import com.tcf.entity.Grade;

public class TestMysql {
	@Test
	public void insert() throws Exception{
		MySqlSqlGenerator gen = new MySqlSqlGenerator();
		Grade grade = new Grade();
		//grade.setGradeId(2);
		EntityInfo info = EntityUtil.getInfo(grade);
		/*System.out.println(gen.genertInsert(info));
		System.out.println(gen.genertUpdate(info,ExpUtil.eq("gradeId", 1)));
		System.out.println(gen.genertDelete(info,ExpUtil.eq("gradeId", 1)));*/
		System.out.println(gen.genertSelect(info,ExpUtil.eq("gradeId", 1), new String[]{"gradeId"}, ExpUtil.eq("gradeId", 1), new Order[]{new Order(new ColumnInfo("gradeId", null), OrderType.desc)}, 0, 12));
	}
}
