package com.tcf.test;

import java.util.List;

import org.junit.Test;
import org.tcf.config.SessionFactory;
import org.tcf.config.impl.PropertiesConfigurationImpl;
import org.tcf.dao.Session;
import org.tcf.sql.entity.Order;
import org.tcf.sql.util.CollectionUtil;
import org.tcf.sql.util.ExpUtil;
import org.tcf.sql.util.StringUtil;

import com.tcf.entity.Grade;
import com.tcf.entity.Student;

public class TestSession {

	SessionFactory sf = new PropertiesConfigurationImpl("dao.properties").buildSessionFactory();
	Session session= sf.getSession();
	/*@Test*/
	public void testSave(){
		Grade obj = new Grade(1, "test");
		session.save(obj);
	}
	/*@Test*/
	public void testUpdate(){
		Grade obj = new Grade(15, "test2");
		session.update(obj);
	}
	/*@Test*/
	public void testDelete(){
		Grade obj = new Grade(15, "bbb");
		session.delete(obj);
	}
	@Test
	public void testFindObj(){
		List<Grade> list1 = session.find("myschool", null, "grade",
				null, ExpUtil.like("gradename", "a"), CollectionUtil.getList(Order.desc("gradeid")), 0, 5, Grade.class);
		System.out.println(StringUtil.listInfo(list1));
		List<Student> list = session.find("select * from student where gradeid=?",Student.class,2);
		System.out.println(StringUtil.listInfo(list));
	}
	
	@Test
	public void testFindList(){
		List list = session.find("select gradename,count(*) from student inner join grade on student.gradeid = grade.gradeid group by gradename");
		System.out.println(StringUtil.listInfo(list));
	}
	@Test
	public void testFindList2(){
		List list = session.find("select * from student");
		System.out.println(StringUtil.listInfo(list));
	}
	@Test
	public void testget(){
		Student stu = (Student) session.get(Student.class, "S1101001");
		System.out.println(stu);
	}
	/*@Test*/
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
	}
	@Test
	public void findExample(){
		Student stu = new Student();
		stu.setGradeId(2);
		String info = StringUtil.listInfo(session.findByExample(stu,0,5,Order.asc("sex")));
		System.out.println(info);
	}
}
