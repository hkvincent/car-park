package vincent.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vincent.utils.SqlHelper;

/**
 * time dao for recording how many time the parking have ran
 * @author vincent @date 2017-4-28
 * @version 1.0.0
 */
public class TimeDao {

	/**
	 * get the time
	 * @param name
	 * @return the value of hour
	 */
	public int select(String name) {
		Connection conn = SqlHelper.getConnection();
		int reuslt = 0;
		Statement stmt = null;
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			ResultSet rsTables = conn.getMetaData().getTables(null, null,
					"timer", null);
			if (rsTables.next()) {
				System.out.println("having table");
			} else {
				stmt.executeUpdate(create());
			}
			ResultSet rs = stmt.executeQuery("select * from timer where name="
					+ "'" + name + "'");
			if (rs.next()) {
				reuslt = Integer.parseInt(rs.getString("value"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlHelper.close(conn, stmt);
		}
		return reuslt;
	}

	/**
	 * update the time
	 * @param name
	 * @param value
	 * @return how many row has been affected
	 */
	public int update(String name, String value) {
		Connection conn = SqlHelper.getConnection();
		Statement stmt = null;
		int rs = 0;
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			ResultSet rsTables = conn.getMetaData().getTables(null, null,
					"timer", null);
			if (rsTables.next()) {
				System.out.println("having table");
			} else {
				stmt.executeUpdate(create());
			}
			String updatesql = "update timer set value = '" + value + "' "
					+ "where name ='" + name + "'";
			rs = stmt.executeUpdate(updatesql);
			conn.commit();
			if (rs < 1) {
				try {
					stmt = conn.createStatement();
					String insertsql = "insert into  timer(name,value) values("
							+ "'" + name + "'" + ", '" + value + "'" + ")";
					rs = stmt.executeUpdate(insertsql);
					conn.commit();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlHelper.close(conn, stmt);
		}
		return rs;
	}

	/**
	 * create table sql will be executed if the table does not exist 
	 * @return create sql
	 */
	public String create() {
		StringBuilder sb = new StringBuilder();
		sb.append("create table timer (id INTEGER PRIMARY KEY,name,value);");
		return sb.toString();
	}
}
