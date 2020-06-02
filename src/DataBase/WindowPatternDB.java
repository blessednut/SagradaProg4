package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.GameDiceModel;
import model.PatternCardFieldModel;

public class WindowPatternDB {
	private Statement st;
	private PreparedStatement ps;

	public WindowPatternDB() {
		this.st = DBCon.getInstance().getSt();
	}

	public PatternCardFieldModel[][] getField(int idPatternCard) {
		try {
			PatternCardFieldModel[][] field = new PatternCardFieldModel[5][4];
			String query = "SELECT *\r\n" + "FROM patterncardfield\r\n" + "WHERE idpatterncard = '" + idPatternCard
					+ "';";
			ResultSet resultset = st.executeQuery(query);

			while (resultset.next()) {
				int x = resultset.getInt("position_x");
				int y = resultset.getInt("position_y");
				field[x - 1][y - 1] = new PatternCardFieldModel(resultset.getInt("idpatterncard"), x, y,
						resultset.getString("color"), resultset.getInt("value"));
			}
			return field;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getName(int idPatternCard) {
		try {
			String query = "SELECT name FROM hjasmeet_db2.patterncard where idpatterncard = " + idPatternCard + ";";
			ResultSet resultset = st.executeQuery(query);

			String name = "";
			while (resultset.next()) {
				name = resultset.getString("name");
			}
			return name;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getDifficulty(int idPatternCard) {
		int difficulty = 0;
		try {
			String query = "SELECT difficulty FROM hjasmeet_db2.patterncard where idpatterncard = " + idPatternCard
					+ ";";
			ResultSet resultset = st.executeQuery(query);

			while (resultset.next()) {
				difficulty = resultset.getInt("difficulty");
			}
			return difficulty;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return difficulty;
	}

	public void makePlayerFrameField(int playerID, int x, int y) {
		try {
			String query = "insert into playerframefield (idplayer, position_x, position_y) values (" + playerID + ","
					+ x + "," + y + ");";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addToPatternCardOption(int idPlayer, int PatternCardID) {
		try {
			String query = "insert into patterncardoption(idpatterncard, idplayer) values(" + PatternCardID + ","
					+ idPlayer + ");";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public int getPlayerID(int gameID, String username) {
		try {
			String query = "select idplayer from player where username = '" + username + "' and idgame = " + gameID
					+ ";";
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

	public void updatePatternCardIDToPlayer(int idplayer, int idpatterncard) {
		try {
			String query = "update player set idpatterncard = " + idpatterncard + " where idplayer = " + idplayer
					+ "; ";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.executeUpdate(query);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public GameDiceModel loadDice(int idplayer, int x, int y) {
		GameDiceModel dice = null;
		try {
			String query = "SELECT * FROM playerframefield AS p JOIN gamedie AS g ON p.idgame = g.idgame AND p.dienumber = g.dienumber AND p.diecolor = g.diecolor WHERE idplayer = " + idplayer + " AND position_x = " + x + " AND position_y = " + y;
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				dice = new GameDiceModel(rs.getInt("idgame"), rs.getInt("dienumber"), rs.getString("diecolor"), rs.getInt("eyes"), rs.getInt("roundtrack"), rs.getInt("roundID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dice;
	}
}
