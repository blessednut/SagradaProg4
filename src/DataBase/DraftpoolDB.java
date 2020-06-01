package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.GameDiceModel;

public class DraftpoolDB {
	private Statement st;
	private PreparedStatement ps;

	public DraftpoolDB() {
		this.st = DBCon.getInstance().getSt();
	}

	public void updateRountrack(int idgame, int dienumber, String diecolor, int roundtrack) {
//		System.out.println("UPDATE ROUNDTRACK");
//		System.out.println("idgame = " + idgame + " dienumber = " + dienumber + " diecolor = " + diecolor
//				+ " roundtrack = " + roundtrack);
		try {
			String query = "UPDATE gamedie set roundtrack = " + roundtrack + " WHERE idgame = " + idgame
					+ " AND dienumber = " + dienumber + " AND diecolor = '" + diecolor + "';";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean draftpoolExists(int idgame, int roundID) {
		try {
			String query = "SELECT * FROM gamedie WHERE idgame = " + idgame + " AND roundID = " + roundID;
			ResultSet resultset = st.executeQuery(query);

			if (resultset.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<GameDiceModel> loadDice(int idgame, int roundID) {
		ArrayList<GameDiceModel> dice = new ArrayList<GameDiceModel>();
		try {
			String query = "select g.idgame, g.dienumber, g.diecolor, g.eyes, g.roundtrack, g.roundID \r\n" + 
					"from gamedie g \r\n" + 
					"where (g.idgame, g.dienumber, g.diecolor) not in (select p.idgame, p.dienumber, p.diecolor from playerframefield p where idgame = " + idgame + ") and idgame = " + idgame + " and roundID = " + roundID + ";";
			ResultSet resultset = st.executeQuery(query);
			//System.out.println("DICELOADING");
			while (resultset.next()) {
//				System.out.println("IDGAME = " + idgame);
//				System.out.println("RoundID = " + roundID);
//				System.out.println("Dienumber = " + resultset.getInt("dienumber"));
//				System.out.println("Diecolor = " + resultset.getString("diecolor"));
//				System.out.println("Eyes = " + resultset.getInt("eyes"));
				dice.add(new GameDiceModel(resultset.getInt("idgame"), resultset.getInt("dienumber"), resultset.getString("diecolor"), resultset.getInt("eyes"), resultset.getInt("roundtrack"), resultset.getInt("roundID")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dice;
	}
	
	public int getHighestRoundID (int idgame) {
		int roundID = 0;
		try {
			String query = "select max(roundID) from gamedie where idgame = " +  idgame;
			ResultSet resultset = st.executeQuery(query);

			if (resultset.next()) {
				roundID = resultset.getInt("max(roundID)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roundID;
	}
}
