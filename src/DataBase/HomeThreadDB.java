package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.HomeThreadModel;

public class HomeThreadDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	private String username;
	private int gameID;
	private HomeThreadModel home;
	
	public HomeThreadDB (HomeThreadModel home) {
		this.home = home;
		this.st = DBCon.getInstance().getSt();
	}
	
	public int getGameID(String username) {
		gameID = 0;
		try {			
			String query = "select idgame from player where username = '" + username + "' and playstatus = 'challengee';";
			rs = st.executeQuery(query);
			while(rs.next()) {
				gameID = rs.getInt("idgame");
				home.addToArray(gameID);
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return gameID;
	}	
	
	
	
	public String getUsernameOfChallenger(int gameid) {
		try {
			String query = "select username from player where idgame = " + gameid + " and playstatus = 'challenger';";
			rs = st.executeQuery(query);
			while(rs.next()) {
				username = rs.getString("username");
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return username;
	}
	
	public int getGameIdForInvite() {
		return gameID;
	}
	
	
	
	
}
