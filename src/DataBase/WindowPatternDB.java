package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.PatternCardFieldModel;
import model.PatternCardModel;

public class WindowPatternDB {
	private ResultSet rs;
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
			rs = st.executeQuery(query);

			while (rs.next()) {
				int x = rs.getInt("position_x");
				int y = rs.getInt("position_y");
				field[x - 1][y - 1] = new PatternCardFieldModel(rs.getInt("idpatterncard"), x, y, rs.getString("color"),
						rs.getInt("value"));
			}
			return field;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getName (int idPatternCard) {
		try {
			String query = "SELECT name FROM hjasmeet_db2.patterncard where idpatterncard = " + idPatternCard + ";";
			rs = st.executeQuery(query);

			String name = "";
			while (rs.next()) {
				name = rs.getString("name");
			}
			return name;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getDifficulty (int idPatternCard) {
		try {
			String query = "SELECT difficulty FROM hjasmeet_db2.patterncard where idpatterncard = " + idPatternCard + ";";
			rs = st.executeQuery(query);

			int difficulty = 0;
			while (rs.next()) {
				difficulty = rs.getInt("difficulty");
			}
			return difficulty;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void makePlayerFrameField(int playerID, int x, int  y) {
		try {
			String query = "insert into playerframefield (idplayer, position_x, position_y) values (" + playerID + "," + x + "," + y + ");";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
