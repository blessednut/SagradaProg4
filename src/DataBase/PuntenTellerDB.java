package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PuntenTellerDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	
	public PuntenTellerDB() {
		this.st = DBCon.getInstance().getSt();
	}
	
	public void updateScore(int score, int idplayer) {
		try {
			String query = "UPDATE player SET score = " + score + "WHERE idplayer = " + idplayer; 
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

