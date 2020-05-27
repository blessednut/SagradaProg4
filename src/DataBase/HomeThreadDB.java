package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.HomeThreadModel;

public class HomeThreadDB {

	private Statement st;
	private PreparedStatement ps;
	private String username;
	private int gameID;
	private HomeThreadModel home;
	private DBCon dbCon;

	public HomeThreadDB(HomeThreadModel home) {
		this.home = home;
		dbCon = new DBCon();
		this.st = dbCon.getSt();

	}

	public int getGameID(String username) {
		gameID = 0;
		try {
			String query = "select idgame from player where username = '" + username
					+ "' and playstatus = 'challengee';";
			ResultSet resultset = st.executeQuery(query);
			while (resultset.next()) {
				gameID = resultset.getInt("idgame");
				home.addToArray(gameID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gameID;
	}

	public String getUsernameOfChallenger(int gameid) {
		try {
			String query = "select username from player where idgame = " + gameid + " and playstatus = 'challenger';";
			ResultSet resultset = st.executeQuery(query);
			if (resultset.next()) {
				username = resultset.getString("username");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return username;
	}

	public int getGameIdForInvite() {
		return gameID;
	}

	public DBCon getDbCon() {
		return dbCon;
	}
	
	

}
