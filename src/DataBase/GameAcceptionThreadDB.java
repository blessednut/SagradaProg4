package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.GameAcceptionThreadModel;

public class GameAcceptionThreadDB {
	private ResultSet amountInvited;
	private ResultSet amountAccepted;
	private ResultSet amountRefused;
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
			amountInvited = st.executeQuery(query);
			if (amountInvited.next()) {
				result = amountInvited.getInt("aantal");
			}
		} catch (Exception e) {

		}
		return result;
	}

	public int getAmountAccepted(int gameID) {
		int result = 0;
		try {
			String query = "select COUNT(username) as aantal from player where idgame = '" + gameID
					+ "' and playstatus = 'challengee';";
			amountAccepted = st.executeQuery(query);
			if (amountAccepted.next()) {
				result = amountAccepted.getInt("aantal");
			}
		} catch (Exception e) {

		}
		return result;
	}

	public int getAmountRefused(int gameID) {
		int result = 0;
		try {
			String query = "select COUNT(username) as aantal from player where idgame = '"+gameID+"' and playstatus = 'refused';";
			amountRefused = st.executeQuery(query);
			if(amountRefused.next()) {
				result = amountRefused.getInt("aantal");
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
