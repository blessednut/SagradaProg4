package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DBCon {

	private Connection con;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;

	public DBCon() {
	}

	public ResultSet getUsername() {
		try {
			createConnection();
			String query = "select * from account";
			rs = st.executeQuery(query);
			System.out.println("ik");
			while (rs.next()) {
				String username = rs.getString("Username");
				String password = rs.getString("Password");

				System.out.print("username = " + username);
				System.out.println(" + password = " + password);

			}
			con.close();

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return rs;
	}

	public void InsertRegister(String username, String password) {
		int id = 9;

		try {
			createConnection();
			String query = "insert into account(UserID,Username,Password) values(" + id + ",?,? )";

			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.execute();
			con.close();

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}

	private void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/sagradatestdb?user=root&password=#Hummel12345");
			st = con.createStatement();

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	public ResultSet getRs() {
		return rs;
	}
	

}
