package com.connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import com.mysql.cj.api.jdbc.Statement;

public class JdbcConnectionPool {

	List<Connection> availableConnections = new ArrayList<>();

	public JdbcConnectionPool() {
		initializeConnectionPool();
	}

	private void initializeConnectionPool() {
		int MIN_POOL_SIZE = Configuration.getInstance().DB_MIN_CONNECTIONS;

		while (!(availableConnections.size() < MIN_POOL_SIZE)) {
			availableConnections.add(createNewConnectionForPool());
		}
	}

	private synchronized boolean checkIfConnectionPoolIsFull() {
		final int MAX_POOL_SIZE = Configuration.getInstance().DB_MAX_CONNECTIONS;

		if (availableConnections.size() < MAX_POOL_SIZE) {
			return false;
		}

		return true;
	}

	private Connection checkExistConnectIsClosed() {
		Connection connection = null;
		for (int i = 0; i < availableConnections.size(); i++) {
			try {
				if (availableConnections.get(i).isClosed()) {
					connection = availableConnections.get(i);
					System.out.println("Connection " + i + " : is Closed.  " + connection);
					return connection;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}

	// Creating a connection
	private Connection createNewConnectionForPool() {
		Configuration config = Configuration.getInstance();
		try {
			Class.forName(config.DB_DRIVER);
			Connection connection = (Connection) DriverManager.getConnection(config.DB_URL, config.DB_USER_NAME,
					config.DB_PASSWORD);
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Connection getConnectionFromPool() {
		Connection connection = null;
		Configuration config = Configuration.getInstance();
		if (!checkIfConnectionPoolIsFull()) {
			connection = createNewConnectionForPool();
			System.out.println("new connection is added");
		} else if (checkExistConnectIsClosed() != null) {
			connection = checkExistConnectIsClosed();
			System.out.println("check exist connection: " + connection);
		} else {
			System.out.println("check timeout connection");
			long startTime = System.currentTimeMillis();
			boolean checkConnWait = true;
			while ((System.currentTimeMillis() - startTime < 3000)) {
				if (checkExistConnectIsClosed() != null) {
					connection = checkExistConnectIsClosed();
					checkConnWait = false;
					break;
				}
			}
			if (checkConnWait == true) {
				System.out.println("Full Connection Error: connection have waited and then	 had been closed!!! ");
			}
		}
		return connection;
	}

	// return colletion to pool
	public synchronized void returnConnectionToPool(Connection connection) {
		availableConnections.add(connection);
	}

	// close connection
	public synchronized void releaseConnection(Connection connection) {
		for (int i = 0; i < availableConnections.size(); i++) {
			Connection temp = availableConnections.get(i);
			if (temp == connection) {
				try {
					System.out.println("connection is closed!!");
					availableConnections.get(i).close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
