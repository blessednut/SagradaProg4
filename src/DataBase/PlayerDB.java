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
			ResultSet resultset = st.executeQuery(query);

			int playerID = 0;
			while (resultset.next()) {
				playerID = resultset.getInt("idplayer");
			}
			return playerID;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public boolean patterncardExists(int idplayer) {
		int idpatterncard = 0;
		try {
			String query = "SELECT idpatterncard FROM player WHERE idplayer = " + idplayer;
			ResultSet resultset = st.executeQuery(query);

			if (resultset.next()) {
				idpatterncard = resultset.getInt("idpatterncard");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (idpatterncard == 0) ? false : true;
	}

	public int getPatterncardID(int idplayer) {
		int idpatterncard = 0;
		try {
			String query = "SELECT idpatterncard FROM player WHERE idplayer = " + idplayer;
			ResultSet resultset = st.executeQuery(query);

			if (resultset.next()) {
				idpatterncard = resultset.getInt("idpatterncard");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idpatterncard;
	}
}
