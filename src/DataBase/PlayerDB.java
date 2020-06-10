package DataBase;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PlayerDB {
	private Statement st;

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

	public boolean getPatterncardChoiceExists(int idplayer) {
		boolean exists = false;
		try {
			String query = "SELECT * FROM patterncardoption WHERE idplayer =" + idplayer;
			ResultSet resultset = st.executeQuery(query);

			if (resultset.next()) {
				exists = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}

	public ArrayList<Integer> getPatterncardOptions(int idplayer) {
		ArrayList<Integer> options = new ArrayList<Integer>();
		try {
			String query = "SELECT * FROM patterncardoption WHERE idplayer =" + idplayer;
			ResultSet resultset = st.executeQuery(query);

			while (resultset.next()) {
				options.add(resultset.getInt("idpatterncard"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return options;
	}
}
