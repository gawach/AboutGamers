package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtility {
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String DB_INFO = "jdbc:mysql://127.0.0.1:3306/techdb";
	public static final String USER_NAME = "seed";
	public static final String USER_PASS = "Tech_123";

	/**
	 * データベース接続
	 * @return 接続情報
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection connectionDb() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME);
		Connection con = DriverManager.getConnection(
				DB_INFO,
				USER_NAME,
				USER_PASS
				);
		return con;
	}

	/**
	 * データベース切断
	 * @param con
	 * @throws SQLException
	 */
	public static void disConnectionDb(Connection con) throws SQLException {
		con.close();
	}


}
