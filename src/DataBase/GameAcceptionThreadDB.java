package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.GameAcceptionThreadModel;

public class GameAcceptionThreadDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;

	private GameAcceptionThreadModel gameAcceptionThread;

	public GameAcceptionThreadDB(GameAcceptionThreadModel gameAcceptionThread) {
		this.gameAcceptionThread = gameAcceptionThread;
		this.st = DBCon.getInstance().getSt();
	}

	public int getAmountInvitited(int gameID) {
		int result = 0;
		try {
			String query = "select COUNT(username) as aantal from player where idgame = '" + gameID + "';";
			rs = st.executeQuery(query);
			if (rs.next()) {
				result = rs.getInt("aantal");
			}
		} catch (Exception e) {

		}
		return result;
	}

	public int getAmountAccepted(int gameID) {
		int result = 0;
		try {
			String query = "select COUNT(username) as aantal from player where idgame = " + gameID
					+ " and playstatus = 'challengee';";
			rs = st.executeQuery(query);
			if (rs.next()) {
				result = rs.getInt("aantal");
			}
		} catch (Exception e) {

		}
		return result;
	}

	public int getAmountRefused(int gameID) {
		int result = 0;
		try {
			String query = "select COUNT(username) as aantal from player where idgame = " + gameID + " and playstatus = 'refused';";
			rs = st.executeQuery(query);
			if(rs.next()) {
				result = rs.getInt("aantal");
			}
		}catch(Exception e) {
			
		}
		return result;
	}
	
	public void setRefused(int gameid) {
		try {
			String query = "update player set playstatus = 'refused' where idgame = "+gameid+";";
			st.execute(query);
		}catch(Exception e) {
			
		}
	}

}
