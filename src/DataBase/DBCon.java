package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBCon {

	private Connection con;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;

	public DBCon() {
	}

	private void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://databases.aii.avans.nl/hjasmeet_db2?user=rjavisse&password=Ab12345");
			st = con.createStatement();

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	public String getPassword(String username) {
		String result = null;
		createConnection();
		try {
			String query = "select Password from account where username = '" + username + "';";
			rs = st.executeQuery(query);
			while(rs.next()) {
				result = rs.getString("Password");

			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return result;
	}

	public ResultSet getRs() {
		return rs;
	}

}
