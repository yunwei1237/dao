package com.tcf.config;

import org.junit.Test;
import org.tcf.config.Configuration;
import org.tcf.config.SessionFactory;
import org.tcf.config.impl.PropertiesConfigurationImpl;

public class TestConfig {
	
	@Test
	public void test(){
		SessionFactory sf = new PropertiesConfigurationImpl("dao.properties").buildSessionFactory();
		System.out.println(sf);
	}
}
