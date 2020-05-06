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
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	
	public QueryDB () {
		this.st = DBCon.getInstance().getSt();
		this.ps = DBCon.getInstance().getPs();
	}
	
	public int getGameID(String playstatus) {
		int gameID = 0;
		try {
			String query = "select game.creationdate, game.idgame from player\r\n" + 
					"left join game on player.idgame = game.idgame\r\n" + 
					"where creationdate = (select max(creationdate) from game)  and player.playstatus like '" + playstatus + "';";
			rs = st.executeQuery(query);
			if(rs.next()) {
				gameID = rs.getInt("idgame");
				System.out.println(rs.getTime("creationdate"));
				System.out.println(rs.getDate("creationdate"));
				System.out.println(rs.getInt("idgame"));
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return gameID;
	}
	
	public String getUsernameOfChallenger(String playstatus) {
		String username = null;
		try {
			String query = "select username from player\r\n" + 
					"where idgame = " + getGameID("Challengee") + " and playstatus like '" + playstatus + "';";
			rs = st.executeQuery(query);
			if(rs.next()) {
				username = rs.getString("username");
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return username;
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
		try {
			String query = "select username from account where username = '" + username + "' ;";
			rs = st.executeQuery(query);
			while (rs.next()) {
				result = rs.getString("username");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	public int maxPlayerId() {
		int playerId = 0;
		try {
			String query = "select MAX(idplayer) as idplayer from player";
			rs = st.executeQuery(query);
			while (rs.next()) {
				playerId = rs.getInt("idplayer");
			}
			playerId++;
		} catch (Exception e) {
			System.out.println(e);
		}
		return playerId;
	}
	
	public void challengeeSelf(int playerID, String username, int gameID, String playerStatus, String playercolor) {
		try {
			String query = "insert into player(idplayer,username,idgame,playstatus,private_objectivecard_color) values('"
					+ playerID + "','" + username + "', '" + gameID + "','" + playerStatus + "','" + playercolor
					+ "');";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void challengeeOther(int playerID, String username, int gameID, String playerStatus, String playercolor) {
		try {
			String query = "insert into player(idplayer,username,idgame,playstatus,private_objectivecard_color) values('"
					+ playerID + "','" + username + "', '" + gameID + "','" + playerStatus + "','" + playercolor
					+ "');";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public String getColor(String color) {
		String result = "";
		try {
			String query = "select color from color where color = '" + color + "';";
			rs = (st.executeQuery(query));
			while (rs.next()) {
				result = rs.getString("color");
			}
		} catch (Exception e) {
			System.out.println("e");
		}
		return result;
	}
	
	public String getPlayerStatus(String playerstatus) {
		String result = "";
		try {
			String query = "SELECT playstatus from playstatus where playstatus = '" + playerstatus + "';";
			rs = st.executeQuery(query);
			while (rs.next()) {
				result = rs.getString("playstatus");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	public int createGameRoom() {
		int x = 0;
		int GameId = 0;;
		try {
			String query = "select MAX(idgame) as idgame from game;";
			rs = st.executeQuery(query);
			while (rs.next()) {
				GameId = rs.getInt("idgame");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		GameId++;
		
		try {
			String query = "insert into game values(?,null,now());";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.setInt(1, GameId);
			ps.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return GameId;
	}
	
	public PatternCardFieldModel[][] getField(int idPatternCard) {
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
			return field;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public PatternCardModel getPatternCard(int idPatternCard) {
		try {
			String query = "SELECT * \r\n" + "FROM patterncard\r\n" + "WHERE idpatterncard = '" + idPatternCard + "';";
			ResultSet rs = DBCon.getInstance().getSt().executeQuery(query);
			rs = st.executeQuery(query);

			while (rs.next()) {
				return new PatternCardModel(rs.getInt("idpatterncard"), rs.getString("name"), rs.getInt("difficulty"),
						rs.getBoolean("standard"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public void registerLogin(String Username, String Password) {
		try {
			String query = "insert into Account values(?,?)";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.setString(1, Username);
			ps.setString(2, Password);
			ps.execute();
			Alert succes = new Alert(AlertType.INFORMATION, "Uw nieuwe account is aangemaakt.", ButtonType.OK);
			succes.showAndWait();
			if (succes.getResult() == ButtonType.OK) {
				succes.close();
			}
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
		try {
			String query = "select password from account where username = '" + username + "';";
			rs = st.executeQuery(query);
			while (rs.next()) {
				result = rs.getString("password");

			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return result;
	}
}
