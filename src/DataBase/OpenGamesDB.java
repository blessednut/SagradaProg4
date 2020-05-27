package DataBase;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

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
		String resultString = "";
		Timestamp resultDate = null;
		try {
			String query = "select player.idgame, game.creationdate from player right join game on player.idgame where game.idgame = player.idgame and username = '" + username+ "' and playstatus = 'accepted';";
			ResultSet resultSet = st.executeQuery(query);
			while(resultSet.next()) {
				result = resultSet.getInt("idgame");
				resultDate = resultSet.getTimestamp("creationdate");
				resultString = Integer.toString(result);
				gamesModel.fillOldGames(resultString, resultDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
