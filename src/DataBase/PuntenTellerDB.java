package DataBase;

import java.sql.PreparedStatement;

public class PuntenTellerDB {

	private PreparedStatement ps;
	
	public PuntenTellerDB() {
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

