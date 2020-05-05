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
import model.DiceModel;
import model.PatternCardFieldModel;
import model.PatternCardModel;

public class DBCon {

	private Connection con;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;

	public DBCon() {
		//createConnection
		//https://www.geeksforgeeks.org/singleton-class-java/
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
				result = rs.getString("password");

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
			}
			con.close();
			} catch (Exception ex) {
			Alert exception = new Alert(AlertType.ERROR,
					"De gebruikersnaam die je wilt gebruiken bestaat al.\nKies een andere gebruiksnaam alstublieft.",
					ButtonType.YES, ButtonType.NO);
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

	public PatternCardModel getPatternCard(int idPatternCard) {
		createConnection();

		try {
			String query = "SELECT * \r\n" + "FROM patterncard\r\n" + "WHERE idpatterncard = '" + idPatternCard + "';";
			rs = st.executeQuery(query);

			while (rs.next()) {
				return new PatternCardModel(rs.getInt("idpatterncard"), rs.getString("name"), rs.getInt("difficulty"),
						rs.getBoolean("standard"));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public PatternCardFieldModel[][] getField(int idPatternCard) {
		createConnection();

		try {
			PatternCardFieldModel[][] field = new PatternCardFieldModel[5][4];
			String query = "SELECT *\r\n" + "FROM patterncardfield\r\n" + "WHERE idpatterncard = '" + idPatternCard
					+ "';";
			rs = st.executeQuery(query);

			while (rs.next()) {
				int x = rs.getInt("position_x");
				int y = rs.getInt("position_y");
				field[x - 1][y - 1] = new PatternCardFieldModel(rs.getInt("idpatterncard"), x, y, rs.getString("color"),
						rs.getInt("value"));
			}con.close();
			return field;
			} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public DiceModel[] importDie() {
		createConnection();

		try {
			DiceModel[] die = new DiceModel[getDieAmount()];
			
			String query = "SELECT *\r\n" + 
					"FROM die;";
			rs = st.executeQuery(query);

			int i = 0;
			while (rs.next()) {
				die[i] = new DiceModel(rs.getInt("number"), rs.getString("color"));
				i++;
			}
			return die;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	private int getDieAmount() {
		createConnection();

		try {
			int value = 0;
			String query = "SELECT COUNT(*) \r\n" + 
					"FROM die";
			rs = st.executeQuery(query);

			while (rs.next()) {
				value = rs.getInt(1);
			}
			
			return value;
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
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