package DataBase;

import java.sql.PreparedStatement;
import java.sql.Statement;

import model.GameModel;

public class DraftpoolDB {
	private Statement st;
	private PreparedStatement ps;
	
	public DraftpoolDB () {
		this.st = DBCon.getInstance().getSt();
	}
	
	public void updateRountrack (int idgame, int dienumber, String diecolor, int roundtrack) {
		System.out.println("UPDATE ROUNDTRACK");
		System.out.println("idgame = " + idgame + " dienumber = " + dienumber + " diecolor = " + diecolor + " roundtrack = " + roundtrack);
		try {
			String query = "UPDATE gamedie set roundtrack = " + roundtrack + " WHERE idgame = " + idgame + " AND dienumber = " + dienumber + " AND diecolor = '" + diecolor + "';";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
