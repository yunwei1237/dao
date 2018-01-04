package com.tcf.util;

import org.junit.Test;
import org.tcf.dao.impl.SessionImpl;
import org.tcf.sql.util.ExpUtil;

import com.tcf.entity.Grade;

public class TestSession {

	@Test
	public void test1(){
		SessionImpl impl= new SessionImpl();
		Grade obj = new Grade(1, "abc");
		impl.save(obj);
		impl.update(obj);
		impl.delete(obj);
		impl.find(Grade.class, ExpUtil.and(ExpUtil.eq("gradename", "abc"),ExpUtil.eq("gradename", "abc")), null, null, null, null, null);
	}
}
