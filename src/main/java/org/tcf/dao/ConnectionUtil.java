package org.tcf.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.tcf.config.ConfigInfo;

public class ConnectionUtil {
	private ConfigInfo config;
	
	public ConnectionUtil(ConfigInfo config) {
		this.config = config;
		try {
			Class.forName(config.getDriver());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection(){
		Connection conn = null;
		try {
			//2.获得连接
			conn = DriverManager.getConnection(config.getUrl(), config.getUsername(),config.getPassword());
			//设置是否自动提交事务
			conn.setAutoCommit(config.getAutoCommit());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
