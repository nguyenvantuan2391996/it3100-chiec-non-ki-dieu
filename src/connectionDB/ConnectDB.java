package connectionDB;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectDB {
	protected Connection conn;
	private static String classforname;
	private static String url;
	private static String user;
	private static String pass;

	/**
	 * lấy thông số kết nối db
	 * @return
	 */
	public Connection getConnectDB() {
		try {
			Properties pro = new Properties();
			pro.load(new FileInputStream("config.properties"));
			classforname = pro.getProperty("classforname");
			url = pro.getProperty("url");
			user = pro.getProperty("user");
			pass = pro.getProperty("pass");
			Class.forName(classforname);
			conn = (Connection) DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.out.println("connect fail !");
		}
		return conn;
	}

	/**
	 * mở kết nối db
	 * @return
	 */
	public Connection openConnectDB() {
		try {
			if (classforname != null && url != null && user != null && pass != null) {
				conn = (Connection) DriverManager.getConnection(url, user, pass);
			} else {
				conn = getConnectDB();
			}
		} catch (SQLException e) {
			System.out.println("connect fail !");
		}
		return conn;
	}
}
