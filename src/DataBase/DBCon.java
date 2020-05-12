package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCon {
	private Connection con;
	private Statement st;

	private static DBCon single_instance;

	private DBCon() {
		createConnection();
		System.out.println("tering");
	}

	public static DBCon getInstance() {
		if (single_instance == null) {
			single_instance = new DBCon();
		}

		return single_instance;
	}

	public void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager
					.getConnection("jdbc:mysql://databases.aii.avans.nl/hjasmeet_db2?user=bboomen&password=Ab12345");
			st = con.createStatement();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	public void closeConnection() {
		try {
			con.close();
			System.out.println("Connection is closed!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Statement getSt() {
		return st;
	}

	public Connection getCon() {
		return con;
	}
}
