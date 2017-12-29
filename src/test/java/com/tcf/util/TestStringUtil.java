package com.tcf.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.tcf.sql.util.StringUtil;

public class TestStringUtil {
	
	@Test
	public void packing(){
		Object names = new String[]{"abc","123","xxx"};
		System.out.println(StringUtil.packing(names, "(", ")", ","));
		List ages = new ArrayList();
		ages.add("132");
		ages.add("33");
		ages.add("44");
		System.out.println(StringUtil.packing(ages, "(", ")", ","));
	}
}
