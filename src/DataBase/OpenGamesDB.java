package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.OpenGamesModel;

public class OpenGamesDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;

	private OpenGamesModel gamesModel;

	public OpenGamesDB(OpenGamesModel gamesModel) {
		this.gamesModel = gamesModel;
		this.st = DBCon.getInstance().getSt();
	}

	public int GetOpenGameID(String username) {
		int result = 0;
		try {
			String query = "select idgame from player where username = '" + username + "' and playstatus = 'accepted'; ";
			ResultSet resultSet = st.executeQuery(query);
			while(resultSet.next()) {
				result = resultSet.getInt("idgame");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
