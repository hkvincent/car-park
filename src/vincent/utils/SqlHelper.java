package vincent.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import vincent.dao.AccountDao;

/**
 * sql helper class for create connection object and close the resource
 * 
 * @author vincent @date 2017-4-28
 * @version 1.0.0
 */
public class SqlHelper {
	// connect to sqlite
	public static Connection conn;
	static {
		try {
			Class.forName("org.sqlite.JDBC");

		} catch (Exception e) {
			System.out.println("no driver in it");

		}
	}

	/**
	 * close connect and statement
	 * 
	 * @param conn
	 * @param st
	 */
	public static void close(Connection conn, Statement st) {
		try {
			if (st != null)
				st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get sqlite connection
	 * 
	 * @return sqlite connection object
	 */
	public static Connection getConnection() {
		URL access = AccountDao.class.getResource("database/user.db");
		String path = access.getPath();
		path = path.substring(1);
		System.out.println(path);
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + path, null,
					null);
		} catch (SQLException e) {
			System.out.println("path error");
			File file = new File("user.db");

			try {
				if (!file.exists()) {
					boolean createNewFile = file.createNewFile();
				}
				conn = DriverManager.getConnection(
						"jdbc:sqlite:" + file.getAbsolutePath(), null, null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return conn;
	}
}
