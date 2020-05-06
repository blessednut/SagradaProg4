package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import model.PatternCardFieldModel;
import model.PatternCardModel;

public class QueryDB {
	//private DBCon con;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	
	public QueryDB () {
		//con = DBCon.getInstance();
		this.st = DBCon.getInstance().getSt();
		this.ps = DBCon.getInstance().getPs();
	}
	
	public boolean checkForDoubleInvite(String username, int idgame, String playstatus) {
		boolean invited = false;
		try {
			String query = "select '" + username + "' from player where idgame = " + idgame + " and playstatus = '"
					+ playstatus + "' ;";
			rs = (st.executeQuery(query));
			if (rs.next()) {
				invited = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return invited;
	}
	
	public String checkInDatabase(String username) {
		String result = null;
		//con.createConnection();
		try {
			String query = "select username from account where username = '" + username + "' ;";
			rs = st.executeQuery(query);
			while (rs.next()) {
				result = rs.getString("username");
			}
			//con.getCon().close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	public int maxPlayerId() {
		int playerId = 0;
		//con.createConnection();
		try {
			String query = "select MAX(idplayer) as idplayer from player";
			rs = st.executeQuery(query);
			while (rs.next()) {
				playerId = rs.getInt("idplayer");
			}
			playerId++;
			//con.getCon().close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return playerId;
	}
	
	public void challengeeSelf(int playerID, String username, int gameID, String playerStatus, String playercolor) {
		//con.createConnection();
		try {
			String query = "insert into player(idplayer,username,idgame,playstatus,private_objectivecard_color) values('"
					+ playerID + "','" + username + "', '" + gameID + "','" + playerStatus + "','" + playercolor
					+ "');";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();

		//	con.getCon().close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void challengeeOther(int playerID, String username, int gameID, String playerStatus, String playercolor) {
//		con.createConnection();
		try {
			String query = "insert into player(idplayer,username,idgame,playstatus,private_objectivecard_color) values('"
					+ playerID + "','" + username + "', '" + gameID + "','" + playerStatus + "','" + playercolor
					+ "');";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
//			con.getCon().close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public String getColor(String color) {
		//con.createConnection();
		String result = "";
		try {
			String query = "select color from color where color = '" + color + "';";
			rs = (st.executeQuery(query));
			while (rs.next()) {
				result = rs.getString("color");
			}
			//con.getCon().close();
		} catch (Exception e) {
			System.out.println("e");
		}
		return result;
	}
	
	public String getPlayerStatus(String playerstatus) {
		String result = "";
		//con.createConnection();
		try {
			String query = "SELECT playstatus from playstatus where playstatus = '" + playerstatus + "';";
			rs = st.executeQuery(query);
			while (rs.next()) {
				result = rs.getString("playstatus");
			}
			//con.getCon().close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	public int createGameRoom() {
		int x = 0;
		int GameId = 0;;
		//con.createConnection();
		try {
			String query = "select MAX(idgame) as idgame from game;";
			rs = st.executeQuery(query);
			while (rs.next()) {
				GameId = rs.getInt("idgame");
			}
		//con.getCon().close();
		} catch (Exception e) {
			System.out.println(e);
		}
		GameId++;
		
		//con.createConnection();
		try {
			String query = "insert into game values(?,null,now());";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.setInt(1, GameId);
			ps.execute();
		//	con.getCon().close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return GameId;
	}
	
	public PatternCardFieldModel[][] getField(int idPatternCard) {
		//createConnection();
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
			}
			//con.close();
			return field;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public PatternCardModel getPatternCard(int idPatternCard) {
		//createConnection();

		try {
			String query = "SELECT * \r\n" + "FROM patterncard\r\n" + "WHERE idpatterncard = '" + idPatternCard + "';";
			ResultSet rs = DBCon.getInstance().getSt().executeQuery(query);
			rs = st.executeQuery(query);

			while (rs.next()) {
				return new PatternCardModel(rs.getInt("idpatterncard"), rs.getString("name"), rs.getInt("difficulty"),
						rs.getBoolean("standard"));
			}
			//con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public void registerLogin(String Username, String Password) {
		//createConnection();

		try {
			String query = "insert into Account values(?,?)";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			//ps = con.prepareStatement(query);
			ps.setString(1, Username);
			ps.setString(2, Password);
			ps.execute();
			Alert succes = new Alert(AlertType.INFORMATION, "Uw nieuwe account is aangemaakt.", ButtonType.OK);
			succes.showAndWait();
			if (succes.getResult() == ButtonType.OK) {
				succes.close();
			}
			//con.close();
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

	public String getPassword(String username) {
		String result = null;
		//createConnection();
		try {
			String query = "select password from account where username = '" + username + "';";
			rs = st.executeQuery(query);
			while (rs.next()) {
				result = rs.getString("password");

			}
		//	con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return result;
	}
}
