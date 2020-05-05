package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import model.GameModel;
import model.PatternCardFieldModel;
import model.PatternCardModel;

public class DBCon {

	private Connection con;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private GameModel m_game;

	public DBCon() {
		this.m_game = new GameModel(this);
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

	public String getPassword(String username) {
		String result = null;
		createConnection();
		try {
			String query = "select password from account where username = '" + username + "';";
			rs = st.executeQuery(query);
			while (rs.next()) {
				result = rs.getString("Password");
				System.out.println(result);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return result;
	}

	public void registerLogin(String Username, String Password) {
		createConnection();

		try {
			String query = "insert into Account values(?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, Username);
			ps.setString(2, Password);
			ps.execute();
			Alert succes = new Alert(AlertType.INFORMATION, "Uw nieuwe account is aangemaakt.", ButtonType.OK);
			succes.showAndWait();
			if (succes.getResult() == ButtonType.OK) {
				succes.close();
				con.close();
			}
		} catch (Exception ex) {	
			Alert exception = new Alert(AlertType.ERROR, "De gebruikersnaam die je wilt gebruiken bestaat al.\\nKies een andere gebruiksnaam alstublieft.", ButtonType.YES,ButtonType.NO);
			exception.showAndWait();
			if (exception.getResult() == ButtonType.YES) {
				exception.close();
			} else {
				Platform.exit();
			}
		}
	}

	public ResultSet getRs() {
		return rs;
	}

	public GameModel getM_game() {
		return m_game;
	}

	public Connection getCon() {
		return con;
	}

	public Statement getSt() {
		return st;
	}

	public PreparedStatement getPs() {
		return ps;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public void setM_game(GameModel m_game) {
		this.m_game = m_game;
	}
	
	
	
	
	
	
	

}
