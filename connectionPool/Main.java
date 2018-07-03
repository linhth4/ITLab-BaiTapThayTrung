package com.connectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		java.sql.Statement st = null;
		ResultSet rs = null;
		Connection con;
		try {
			con = DataSource.getConnection();
			st = con.createStatement();
			String sql = "INSERT INTO user1 (id,username, pass) VALUES (NULL, 'linh', '1')";
			int x = st.executeUpdate(sql);
			System.out.println(x);

			String sql1 = "INSERT INTO user1 (id,username, pass) VALUES (?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql1);

			pst.setString(1, null);
			pst.setString(2, "t");
			pst.setString(3, "password is hello");
			int r = pst.executeUpdate();
			System.out.println(r);

			Connection con1 = DataSource.getConnection();
			Connection con2 = DataSource.getConnection();
			Connection con3 = DataSource.getConnection();
			Connection con4 = DataSource.getConnection();
			Connection con5 = DataSource.getConnection();
			Connection con6 = DataSource.getConnection();
			
			
			DataSource.releaseConnection(con1);
			DataSource.releaseConnection(con2);
			DataSource.releaseConnection(con3);
			DataSource.releaseConnection(con4);
			DataSource.releaseConnection(con5);
			DataSource.releaseConnection(con6);

			
			Connection con7 = DataSource.getConnection();
			System.out.println("connection last: "+con7);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
