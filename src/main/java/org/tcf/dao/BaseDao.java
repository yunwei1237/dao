package org.tcf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 1.获得连接
 * 2.获得执行sql的对象 
 * 3.执行查询的sql 查询
 * 4.执行非查询的sql 增删改  crud    create,retrieve,update,delte
 * @author Archer Tan
 *
 */
public class BaseDao {
	private Connection conn;
	private PreparedStatement state;
	private ResultSet rs;
	
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public BaseDao(Connection conn) {
		super();
		this.conn = conn;
	}
	public PreparedStatement getStatement(String sql,Object...params){
		try {
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
	public int executeNonQuery(String sql,Object...params){
		try {
			return getStatement(sql,params).executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public ResultSet executeQuery(String sql,Object...params){
		try {
			return getStatement(sql,params).executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void close(){
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
