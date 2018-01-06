package org.tcf.config;

public interface Configuration {
	
	/**
	 * 读取配置文件，创建配置对象
	 * 通过配置文件获得SessionFactory
	 * @param config
	 * @return
	 */
	SessionFactory buildSessionFactory();
}
