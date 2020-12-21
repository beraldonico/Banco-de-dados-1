package org.model;

public class PropriedadeConexao {
	
	private int id;
	private String driver = "";
	private String url = "";
	private String user = "";
	private String password = "";

	public PropriedadeConexao(int id, final String driver, final String url, 
			final String user, final String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
	
}
