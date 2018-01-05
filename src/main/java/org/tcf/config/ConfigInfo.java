package org.tcf.config;


public class ConfigInfo {
	private String driver;
	private String url;
	private String username;
	private String password;
	private Boolean autoCommit;
	private String generatorClass;
	public ConfigInfo() {
		// TODO Auto-generated constructor stub
		this(false,"org.tcf.sql.generator.impl.MySqlSqlGenerator");
	}
	public ConfigInfo(String driver, String url, String username,
			String password, Boolean autoCommit, String generatorClass) {
		super();
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
		this.autoCommit = autoCommit;
		this.generatorClass = generatorClass;
	}
	public ConfigInfo(Boolean autoCommit, String generatorClass) {
		super();
		this.autoCommit = autoCommit;
		this.generatorClass = generatorClass;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getAutoCommit() {
		return autoCommit;
	}
	public void setAutoCommit(Boolean autoCommit) {
		this.autoCommit = autoCommit;
	}
	public String getGeneratorClass() {
		return generatorClass;
	}
	public void setGeneratorClass(String generatorClass) {
		this.generatorClass = generatorClass;
	}
	@Override
	public String toString() {
		return "ConfigInfo [driver=" + driver + ", url=" + url + ", username="
				+ username + ", password=" + password + ", autoCommit="
				+ autoCommit + ", generatorClass=" + generatorClass + "]";
	}
}
