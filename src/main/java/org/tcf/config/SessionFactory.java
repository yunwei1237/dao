package org.tcf.config;

import org.tcf.dao.ConnectionUtil;
import org.tcf.dao.Session;
import org.tcf.dao.impl.SessionImpl;
import org.tcf.exception.DaoException;
import org.tcf.sql.generator.SqlGenerator;

public class SessionFactory {
	private ConfigInfo config;
	private SqlGenerator gen = null;
	private ConnectionUtil cu = null;
	public SessionFactory(ConfigInfo config) {
		super();
		this.config = config;
		cu = new ConnectionUtil(config);
		try {
			gen = (SqlGenerator) Class.forName(config.getGeneratorClass()).newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}
	}
	public Session getSession(){
		return new SessionImpl(gen,  cu.getConnection());
	}
	@Override
	public String toString() {
		return "SessionFactory [config=" + config + "]";
	}
	
}
