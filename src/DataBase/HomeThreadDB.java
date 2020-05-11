package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class HomeThreadDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	private String username;
	private int gameID;
	
	public HomeThreadDB () {
		this.st = DBCon.getInstance().getSt();
	}
	
	public int getGameID() {
		gameID = 0;
		try {
//			String query = "Select MAX(creationdate) as creationdate from player left join game on player.idgame where player.idgame = game.idgame and username = '" + username + "' and playstatus = '" + playstatus + "'; ";
//			String query = "select game.creationdate, game.idgame from player\r\n" + 
//					"left join game on player.idgame = game.idgame\r\n" + 
//					"where creationdate = (select max(creationdate) from game)  and player.playstatus like '" + playstatus + "';";
			
			String query = "select MAX(idgame) as idgame, Max(creationdate) as creationdate from game;";
			rs = st.executeQuery(query);
			System.out.println("kut");
			if(rs.next()) {
				gameID = rs.getInt("idgame");

			}
		}catch(Exception e){
			System.out.println(e);
		}
		return gameID;
	}
	
	public String getUsernameOfChallenger(String playstatus) {
		try {
			System.out.println("hey");
			String query = "select username, idgame from player\r\n" + 
					"where idgame = " + getGameID() + " and playstatus like '" + playstatus + "';";
			rs = st.executeQuery(query);
			if(rs.next()) {
				username = rs.getString("username");
				gameID = rs.getInt("idgame");
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
