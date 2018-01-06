package org.tcf.config.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import org.tcf.config.ConfigInfo;
import org.tcf.config.Configuration;
import org.tcf.config.SessionFactory;
import org.tcf.sql.util.StringUtil;

public class PropertiesConfigurationImpl implements Configuration {
	private String config;
	
	public PropertiesConfigurationImpl(String config) {
		super();
		this.config = config;
	}

	private SessionFactory read(String config) {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		InputStream is = PropertiesConfigurationImpl.class.getClassLoader().getResourceAsStream(config);
		ConfigInfo info = new ConfigInfo();
		try {
			props.load(is);
			for(Field field:ConfigInfo.class.getDeclaredFields()){
				String val = props.getProperty(field.getName());
				if(val != null){
					field.setAccessible(true);
					field.set(info, StringUtil.convertType(val, field.getType()));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SessionFactory(info);
	}

	@Override
	public SessionFactory buildSessionFactory() {
		// TODO Auto-generated method stub
		return read(config);
	}

}
