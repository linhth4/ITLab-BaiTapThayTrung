package com.connectionPool;

public class Configuration {
	public String DB_USER_NAME;

	public String DB_PASSWORD;

	public String DB_URL;

	public String DB_DRIVER;

	public String DB_NAME;
	
	public Integer DB_MAX_CONNECTIONS;
	public Integer DB_MIN_CONNECTIONS;
	
	public Configuration() {
		init();
	}

	private static Configuration configuration = new Configuration();

	public static Configuration getInstance() {
		return configuration;
	}

	private void init() {
		DB_USER_NAME = "root";
		DB_PASSWORD = "";
		DB_NAME="jdbc";
		DB_URL = "jdbc:mysql://localhost:8080/" +DB_NAME;
		DB_DRIVER = "com.mysql.cj.jdbc.Driver";
		DB_MAX_CONNECTIONS = 4;
		DB_MIN_CONNECTIONS=2;
	}
}
