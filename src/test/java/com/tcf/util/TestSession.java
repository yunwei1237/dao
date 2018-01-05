package com.tcf.util;

import java.util.List;

import org.junit.Test;
import org.tcf.config.SessionFactory;
import org.tcf.config.impl.PropertiesConfigurationImpl;
import org.tcf.dao.Session;
import org.tcf.sql.entity.Order;
import org.tcf.sql.util.CollectionUtil;
import org.tcf.sql.util.StringUtil;

import com.tcf.entity.Grade;
import com.tcf.entity.Student;

public class TestSession {

	SessionFactory sf = new PropertiesConfigurationImpl().config("dao.properties");
	Session session= sf.getSession();
	public void test1(){
		Grade obj = new Grade(1, "abc");
		/*session.save(obj);
		session.update(obj);
		session.delete(obj);*/
		///session.find("myschool", null, "grade", columns, where, having, orders, begin, size, clazz);
		List<Grade> list = session.find("select * from Grade",null, Grade.class);
		System.out.println(StringUtil.listInfo(list));
	}
	/*@Test
	public void testSave(){
		Grade obj = new Grade(1, "test");
		session.save(obj);
	}*/
	/*@Test
	public void testUpdate(){
		Grade obj = new Grade(15, "test2");
		session.update(obj);
	}*/
	/*@Test
	public void testDelete(){
		Grade obj = new Grade(15, "bbb");
		session.delete(obj);
	}*/
	/*@Test*/
	public void testFindObj(){
		//List<Grade> list = session.find("select * from Grade", Grade.class);
		//List<Grade> list = session.find("myschool", null, "grade",
		//		CollectionUtil.getList(new ColumnInfo("gradeid", null),new ColumnInfo("gradename", null)), ExpUtil.like("gradename", "a"), CollectionUtil.getList(Order.desc("gradeid")), 0, 5, Grade.class);
		/*List<Grade> list = session.find("myschool", null, "grade",
				null, ExpUtil.like("gradename", "a"), CollectionUtil.getList(Order.desc("gradeid")), 0, 5, Grade.class);
		System.out.println(StringUtil.listInfo(list));*/
		List<Student> list = session.find("select * from student", CollectionUtil.getList(null),Student.class);
		System.out.println(StringUtil.listInfo(list));
	}
	
	/*@Test*/
	public void testFindList(){
		//List list = session.find("select count(*),avg(gradeid) from grade group by gradeid>5");
		/*List list = session.find(
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
		/*List list = session.find(
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
	/*@Test*/
	public void testget(){
		Student stu = (Student) session.get(Student.class, "S1101001");
		System.out.println(stu);
	}
	/*@Test
	public void testTransaction(){
		Grade g1 = new Grade(1, "test3");
		Grade g2 = new Grade(2, "test4");
		try {
			session.save(g1);
			session.save(g2);
			session.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();
		}
	}*/
	@Test
	public void findExample(){
		Student stu = new Student();
		stu.setGradeId(2);
		String info = StringUtil.listInfo(session.findByExample(stu,0,5,Order.asc("sex")));
		System.out.println(info);
	}
}
