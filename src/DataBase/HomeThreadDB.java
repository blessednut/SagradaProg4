package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class HomeThreadDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	
	public HomeThreadDB () {
		this.st = DBCon.getInstance().getSt();
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
}
