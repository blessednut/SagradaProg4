package DataBase;

import java.sql.ResultSet;
import java.sql.Statement;

public class GameAcceptionThreadDB {
	private ResultSet amountInvited;
	private ResultSet amountAccepted;
	private ResultSet amountRefused;
	private Statement st;
	private DBCon conNection;

	public GameAcceptionThreadDB() {
		this.conNection = new DBCon();
		this.st = conNection.getSt();
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
			e.printStackTrace();
		}
		return result;
	}

	public int getAmountAccepted(int gameID) {
		int result = 0;
		try {
			String query = "select COUNT(username) as aantal from player where idgame = " + gameID
					+ " and playstatus = 'challengee';";
			amountAccepted = st.executeQuery(query);
			if (amountAccepted.next()) {
				result = amountAccepted.getInt("aantal");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getAmountRefused(int gameID) {
		int result = 0;
		try {
			String query = "select COUNT(username) as aantal from player where idgame = "+gameID+" and playstatus = 'refused';";
			amountRefused = st.executeQuery(query);
			if(amountRefused.next()) {
				result = amountRefused.getInt("aantal");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void setRefused(int gameid) {
		try {
			String query = "update player set playstatus = 'refused' where idgame = "+gameid+";";
			st.execute(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setAccepted(int gameid) {
		try {
			String query = "update player set playstatus = 'accepted' where idgame = "+gameid+" and playstatus = 'challenger';";
			st.execute(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public DBCon getConNection() {
		return conNection;
	}


}
