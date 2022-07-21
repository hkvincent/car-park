package vincent.dao;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vincent.entity.User;
import vincent.utils.SqlHelper;

/**
 * sql helper to connect database for login and register
 * 
 * @author vincent @date 2017-4-27
 * @version 1.0.0
 */
public class AccountDao {
	public Connection conn;

	/**
	 * select query in DAL(data access layer) is for BLL(business logic layer)
	 * login
	 * 
	 * @param user
	 * @return the login user information
	 */
	public User select(User user) {
		conn = SqlHelper.getConnection();
		Statement stmt = null;
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			ResultSet rsTables = conn.getMetaData().getTables(null, null,
					"user", null);
			if (rsTables.next()) {
				System.out.println("having table");
			} else {
				stmt.executeUpdate(create());
			}
			ResultSet rs = stmt
					.executeQuery("select * from user where username=" + "'"
							+ user.getUsername() + "'" + "and password = '"
							+ user.getPassword() + "'");
			if (rs.next()) {
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("username"));
			} else {
				user = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlHelper.close(conn, stmt);
		}
		return user;

	}

	/**
	 * insert sql in DAL is for BLL register logic
	 * 
	 * @param user
	 * @return how many row are infected by this sql
	 */
	public int insert(User user) {
		conn = SqlHelper.getConnection();
		Statement stmt = null;
		int rs = 0;
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			ResultSet rsTables = conn.getMetaData().getTables(null, null,
					"user", null);
			if (rsTables.next()) {
				System.out.println("having table");
			} else {
				stmt.executeUpdate(create());
			}
			String sql = "insert into  user(username,password) values(" + "'"
					+ user.getUsername() + "'" + ", '" + user.getPassword()
					+ "'" + ")";
			rs = stmt.executeUpdate(sql);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlHelper.close(conn, stmt);
		}
		return rs;
	}

	/**
	 * sql helper for generate sql
	 * 
	 * @return sql
	 */
	public String create() {
		StringBuilder sb = new StringBuilder();
		sb.append("create table user (id INTEGER PRIMARY KEY,username,password);");
		return sb.toString();
	}

}
