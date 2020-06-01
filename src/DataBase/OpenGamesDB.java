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
			String query = "select player.idgame, game.creationdate from player right join game on player.idgame where game.idgame = player.idgame and username = '"
					+ username + "' and playstatus = 'accepted';";
			ResultSet resultSet = st.executeQuery(query);
			while (resultSet.next()) {
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

	public void getgameIDs() {
		int resultint = 0;
		Timestamp resultDate = null;
		String result = "";
		try {
			String query = "SELECT game.idgame, game.creationdate FROM game left join player on player.idgame\r\n"
					+ "where player.idgame = game.idgame\r\n" + "group by game.idgame\r\n"
					+ "order by game.creationdate asc;";
			ResultSet resultset = st.executeQuery(query);
			while (resultset.next()) {
				resultint = resultset.getInt("idgame");
				resultDate = resultset.getTimestamp("creationdate");
				result = Integer.toString(resultint);

				gamesModel.fillArrayForGames(result, resultDate);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getOwnGamesID(String username) {
		int result = 0;
		String resultString = "";
		try {
			String query = "select game.idgame from game left join player on player.idgame where player.idgame = game.idgame and username = '"+username+"';";
			ResultSet resultSet = st.executeQuery(query);
			while (resultSet.next()) {
				result = resultSet.getInt("idgame");
				resultString = Integer.toString(result);
				gamesModel.fillArrayOwnGames(resultString);
				System.out.println(resultString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}

}
