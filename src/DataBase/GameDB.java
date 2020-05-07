package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class GameDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	
	public GameDB () {
		this.st = DBCon.getInstance().getSt();
	}
	
	public int createGameRoom() {
		int GameId = 0;
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
}
