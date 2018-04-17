package connectionDB;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import view.NoticeMessage;

public class ConnectDB {
	protected Connection conn;

	public Connection getConnectDB() {
		try {
			Properties pro = new Properties();
			pro.load(new FileInputStream("config.properties"));
			String classforname = pro.getProperty("classforname");
			String url = pro.getProperty("url");
			String user = pro.getProperty("user");
			String pass = pro.getProperty("pass");
			Class.forName(classforname);
			conn = (Connection) DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.out.println("connect fail !");
		}
		return conn;
	}
}
