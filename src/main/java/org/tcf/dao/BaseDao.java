package org.tcf.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * 1.获得连接
 * 2.获得执行sql的对象 
 * 3.执行查询的sql 查询
 * 4.执行非查询的sql 增删改  crud    create,retrieve,update,delte
 * @author Archer Tan
 *
 */
public class BaseDao {
	
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	private static Connection conn;
	private static PreparedStatement state;
	private static ResultSet rs;
	//类被加载的时候执行
	static{
		Properties props = new Properties();
		
		
		try {
			//从src目录下读取
			InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("jdbc.properties");
			props.load(is);
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			username = props.getProperty("username");
			password = props.getProperty("password");
			//1.加载驱动
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static Connection getConnection(){
		try {
			//2.获得连接
			conn = DriverManager.getConnection(url, username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	private static PreparedStatement getStatement(String sql,Object...params){
		try {
			if(conn == null || conn.isClosed()) getConnection();
			state = conn.prepareStatement(sql);
			for(int i = 0;i<params.length;i++){
				state.setObject(i+1, params[i]);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	public static int executeNonQuery(String sql,Object...params){
		try {
			return getStatement(sql,params).executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public static ResultSet executeQuery(String sql,Object...params){
		try {
			return getStatement(sql,params).executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void close(){
		try {
			if(rs != null) rs.close();
			if(state != null) state.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
