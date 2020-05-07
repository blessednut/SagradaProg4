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

public class InviteDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	
	public InviteDB () {
		this.st = DBCon.getInstance().getSt();
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
}
