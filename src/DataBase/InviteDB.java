package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class InviteDB {
	private Statement st;
	private PreparedStatement ps;

	public InviteDB() {
		this.st = DBCon.getInstance().getSt();
	}

	public boolean checkForDoubleInvite(String ownUsername, String opponendUsername) {
		boolean invited = false;
		try {
//			String query = "select '" + username + "' from player where idgame = " + idgame + " and playstatus = '"
//					+ playstatus + "' ;";
			String query = "select * from player where idgame = any(select idgame from player where username = '"+ ownUsername +"' and playstatus = 'challenger') and username = '"+ opponendUsername +"' and playstatus = 'challengee';";
			ResultSet resultset = (st.executeQuery(query));
			if (resultset.next()) {
				invited = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invited;
	}

	public String checkInDatabase(String username) {
		String result = null;
		try {
			String query = "select username from account where username = '" + username + "' ;";
			ResultSet resultset = st.executeQuery(query);
			while (resultset.next()) {
				result = resultset.getString("username");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int maxPlayerId() {
		int playerId = 0;
		try {
			String query = "select MAX(idplayer) as idplayer from player";
			ResultSet resultset = st.executeQuery(query);
			while (resultset.next()) {
				playerId = resultset.getInt("idplayer");
			}
			playerId++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playerId;
	}

	public void challengeeSelf(int playerID, String username, int gameID, String playerStatus, String playercolor) {
		try {
			String query = "insert into player(idplayer, username, idgame, playstatus, seqnr, private_objectivecard_color) values('"
					+ playerID + "','" + username + "', '" + gameID + "','" + playerStatus + "', " + getSeqNR(gameID) + ", '" + playercolor
					+ "');";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void challengeeOther(int playerID, String username, int gameID, String playerStatus, String playercolor) {
		try {
			String query = "insert into player(idplayer, username, idgame, playstatus, seqnr, private_objectivecard_color) values('"
					+ playerID + "','" + username + "', '" + gameID + "','" + playerStatus + "', " + getSeqNR(gameID) + ",'" + playercolor
					+ "');";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int getSeqNR (int gameID) {
		int newSeqNR = 0;
		try {
			String query = "SELECT COUNT(*) + 1 AS seqNR\r\n" + 
					"FROM player\r\n" + 
					"WHERE idgame = " + gameID;
			ResultSet resultset = (st.executeQuery(query));
			while (resultset.next()) {
				newSeqNR = resultset.getInt("seqNR");
			}
		} catch (Exception e) {
			//System.out.println("e");
		}
		return newSeqNR;
	}

	public String getColor(String color) {
		String result = "";
		try {
			String query = "select color from color where color = '" + color + "';";
			ResultSet resultset = (st.executeQuery(query));
			while (resultset.next()) {
				result = resultset.getString("color");
			}
		} catch (Exception e) {
			//System.out.println("e");
		}
		return result;
	}

	public String getPlayerStatus(String playerstatus) {
		String result = "";
		try {
			String query = "SELECT playstatus from playstatus where playstatus = '" + playerstatus + "';";
			ResultSet resultset = st.executeQuery(query);
			while (resultset.next()) {
				result = resultset.getString("playstatus");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void updatePlayerStatusChallengee(String username, int gameid, String playstatus) {
		try {
			String query = "update player set playstatus = '" + playstatus + "' where username = '" + username
					+ "' and idgame = " + gameid + " and playstatus = 'challengee';";
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePlayerStatusChallenger(String username, int gameid) {
		try {
			String query = "update player set playstatus = 'accepted' where username = '" + username + "' and idgame = "
					+ gameid + " and playstatus = 'challenger';";
			st.executeUpdate(query);
		} catch (Exception e) {

		}
	}

	// checken voor hoeveelheid accepted.
	public int getAcceptedAmount(int gameid) {
		int result = 0;

		try {
			String query = "select count(username) as amountAccepted from player where idgame = " + gameid
					+ " and playstatus = 'accepted';";
			ResultSet resultset = st.executeQuery(query);
			if (resultset.next()) {
				result = resultset.getInt("AmountAccepted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getRefusedAmount(int gameid) {
		int result = 0;

		try {
			String query = "select count(username) as amountRefused from player where idgame = " + gameid
					+ " and playstatus = 'refused';";
			ResultSet resultset = st.executeQuery(query);
			if (resultset.next()) {
				result = resultset.getInt("amountRefused");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int getInviteGameID() {
		int result = 0;
		
		try {
			String query = "select MAX(idgame) as idplayer from player";
			ResultSet resultset = st.executeQuery(query);
			while (resultset.next()) {
				result = resultset.getInt("idplayer");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int checkInvitation(String challenger, String challengee) {
		int result = 0;
		
		try {
			String query = "select count(challengee.playstatus) as aantal from player challengee join player challenger on challengee.idgame = challenger.idgame where challengee.playstatus = 'challengee' and challenger.username = '"+challenger+"' and challengee.username = '"+challengee+"' group by challengee.username;";
			ResultSet resultset = st.executeQuery(query);
			while(resultset.next()) {
				result = resultset.getInt("aantal");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<String> getUsernames(String currentPlayer) {
		ArrayList<String> usernames = new ArrayList<>();
		try {
			String query = "select * from account";
			ResultSet resultset = st.executeQuery(query);
			while(resultset.next()) {
				String username = resultset.getString("username");
				if (!username.equals(currentPlayer)) {
					usernames.add(username);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usernames;
	}
	
}
