package com.tcf.util;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.tcf.config.Configuration;
import org.tcf.config.SessionFactory;
import org.tcf.config.impl.PropertiesConfigurationImpl;
import org.tcf.dao.Session;
import org.tcf.dao.impl.SessionImpl;
import org.tcf.sql.entity.ColumnInfo;
import org.tcf.sql.entity.Order;
import org.tcf.sql.util.CollectionUtil;
import org.tcf.sql.util.ExpUtil;
import org.tcf.sql.util.FunUtil;
import org.tcf.sql.util.StringUtil;

import com.tcf.entity.Grade;
import com.tcf.entity.Student;

public class TestSession {

	SessionFactory sf = new PropertiesConfigurationImpl().config("dao.properties");
	Session impl= sf.getSession();
	public void test1(){
		Grade obj = new Grade(1, "abc");
		/*impl.save(obj);
		impl.update(obj);
		impl.delete(obj);*/
		///impl.find("myschool", null, "grade", columns, where, having, orders, begin, size, clazz);
		List<Grade> list = impl.find("select * from Grade",null, Grade.class);
		System.out.println(StringUtil.listInfo(list));
	}
	/*@Test
	public void testSave(){
		Grade obj = new Grade(1, "test");
		impl.save(obj);
	}*/
	/*@Test
	public void testUpdate(){
		Grade obj = new Grade(15, "test2");
		impl.update(obj);
	}*/
	/*@Test
	public void testDelete(){
		Grade obj = new Grade(15, "bbb");
		impl.delete(obj);
	}*/
	/*@Test*/
	public void testFindObj(){
		//List<Grade> list = impl.find("select * from Grade", Grade.class);
		//List<Grade> list = impl.find("myschool", null, "grade",
		//		CollectionUtil.getList(new ColumnInfo("gradeid", null),new ColumnInfo("gradename", null)), ExpUtil.like("gradename", "a"), CollectionUtil.getList(Order.desc("gradeid")), 0, 5, Grade.class);
		/*List<Grade> list = impl.find("myschool", null, "grade",
				null, ExpUtil.like("gradename", "a"), CollectionUtil.getList(Order.desc("gradeid")), 0, 5, Grade.class);
		System.out.println(StringUtil.listInfo(list));*/
		List<Student> list = impl.find("select * from student", CollectionUtil.getList(null),Student.class);
		System.out.println(StringUtil.listInfo(list));
	}
	
	/*@Test*/
	public void testFindList(){
		//List list = impl.find("select count(*),avg(gradeid) from grade group by gradeid>5");
		/*List list = impl.find(
				"myschool",
				null, 
				"grade",//catelog,schema,table
				(List<ColumnInfo>)CollectionUtil.getList(new ColumnInfo("gradeid", FunUtil.count())),//columns
				ExpUtil.like("gradename", "a"),//where
				(List<String>)CollectionUtil.getList("gradename"), //groups
				ExpUtil.like("gradename", "a"), //having
				(List<Order>)CollectionUtil.getList(Order.desc("gradeid")),//orders
				0, 10);//begin, size
*/		
		/*List list = impl.find(
				"myschool",
				null, 
				"grade",//catelog,schema,table
				null,//columns
				ExpUtil.like("gradename", "a"),//where
				(List<ColumnInfo>)CollectionUtil.getList(new ColumnInfo("gradename")), //groups
				ExpUtil.like("gradename", "a"), //having
				(List<Order>)CollectionUtil.getList(Order.desc("gradeid")),//orders
				0, 10);//begin, size
		System.out.println(StringUtil.listInfo(list));*/
	}
	@Test
	public void testget(){
		Student stu = (Student) impl.get(Student.class, "S1101001");
		System.out.println(stu);
	}
	/*@Test
	public void testTransaction(){
		Grade g1 = new Grade(1, "test3");
		Grade g2 = new Grade(2, "test4");
		try {
			impl.save(g1);
			impl.save(g2);
			impl.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			impl.rollback();
		}
	}*/
}
