package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PlayerDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;

	public PlayerDB() {
		this.st = DBCon.getInstance().getSt();
	}

	public int getPlayerID(int gameID, String username) {
		try {
			String query = "select idplayer from player where username = '" + username + "' and idgame = " + gameID + ";";
			rs = st.executeQuery(query);

			int playerID = 0;
			while (rs.next()) {
				playerID = rs.getInt("idplayer");
			}
			return playerID;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
