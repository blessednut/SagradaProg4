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
			System.out.println(e);
		}
		return null;
	}

	public PatternCardModel getPatternCard(int idPatternCard) {
		try {
			String query = "SELECT * \r\n" + "FROM patterncard\r\n" + "WHERE idpatterncard = '" + idPatternCard + "';";
			ResultSet rs = DBCon.getInstance().getSt().executeQuery(query);
			rs = st.executeQuery(query);

			while (rs.next()) {
				return new PatternCardModel(rs.getInt("idpatterncard"), rs.getString("name"), rs.getInt("difficulty"),
						rs.getBoolean("standard"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
