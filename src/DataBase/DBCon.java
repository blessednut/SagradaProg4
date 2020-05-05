package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import model.PatternCardFieldModel;
import model.PatternCardModel;

public class DBCon {
	protected Connection con;
	protected Statement st;
	protected PreparedStatement ps;
	protected ResultSet rs;
	
	private static DBCon single_instance;
	
	public DBCon () {
		createConnection();
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

	public ResultSet getRs() {
		return rs;
	}

	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public PreparedStatement getPs() {
		return ps;
	}

	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
}
