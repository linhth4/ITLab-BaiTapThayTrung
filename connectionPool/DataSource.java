package com.connectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
	static JdbcConnectionPool pool = new JdbcConnectionPool();

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connection = pool.getConnectionFromPool();
		return connection;
	}
	 public static void returnConnection(Connection connection) {
		  pool.returnConnectionToPool(connection);
		 }
	public static void releaseConnection(Connection conn) {
		pool.releaseConnection(conn);
	}
}
