package model;

import Controller.InviteController;
import DataBase.DBCon;
import DataBase.QueryDB;

public class InviteModel {

	private QueryDB con;
	private InviteController c_invite;

	public InviteModel(InviteController c_invite) {
		this.c_invite = c_invite;
		con = new QueryDB();
	}

	// checkt of je de persoon al hebt uitgenodigd
//	public boolean checkForDoubleInvite(String username, int idgame, String playstatus) {
//		boolean invited = false;
//		//con.createConnection();
//		try {
//			String query = "select '" + username + "' from player where idgame = " + idgame + " and playstatus = '"
//					+ playstatus + "' ;";
//			con.setRs(con.getSt().executeQuery(query));
//			if (con.getRs().next()) {
//				invited = true;
//			}
//			//con.getCon().close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return invited;
//	}

	public boolean checkForDoubleInvite(String username, int idgame, String playstatus) {
		return new QueryDB().checkForDoubleInvite(username, idgame, playstatus);
	}

	// haalt username op uit db.
//	public String checkInDatabase(String username) {
//		String result = null;
//		//con.createConnection();
//		try {
//			String query = "select username from account where username = '" + username + "' ;";
//			con.setRs(con.getSt().executeQuery(query));
//			while (con.getRs().next()) {
//				result = con.getRs().getString("username");
//			}
//			//con.getCon().close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return result;
//	}

	public String checkInDatabase(String username) {
		return new QueryDB().checkInDatabase(username);
	}

	// haalt max(idplayer) op uit db en verhoodt dit met 1 en stuurd terug als int.
//	public int maxPlayerId() {
//		int playerId = 0;
//		//con.createConnection();
//		try {
//			String query = "select MAX(idplayer) as idplayer from player";
//			con.setRs(con.getSt().executeQuery(query));
//			while (con.getRs().next()) {
//				playerId = con.getRs().getInt("idplayer");
//			}
//			playerId++;
//			//con.getCon().close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return playerId;
//	}

	public int maxPlayerId() {
		return new QueryDB().maxPlayerId();
	}

	// vult player table in de db voor eigen username.
//	public void challengeeSelf(int playerID, String username, int gameID, String playerStatus, String playercolor) {
//		//con.createConnection();
//		try {
//			String query = "insert into player(idplayer,username,idgame,playstatus,private_objectivecard_color) values('"
//					+ playerID + "','" + username + "', '" + gameID + "','" + playerStatus + "','" + playercolor
//					+ "');";
//			con.setPs(con.getCon().prepareStatement(query));
//			con.getPs().execute();
//
//		//	con.getCon().close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}

	public void challengeeSelf(int playerID, String username, int gameID, String playerStatus, String playercolor) {
		new QueryDB().challengeeSelf(playerID, username, gameID, playerStatus, playercolor);
	}

	// vult player tabel in de db voor de uitgedaagde speler.
//	public void challengeeOther(int playerID, String username, int gameID, String playerStatus, String playercolor) {
//		con.createConnection();
//		try {
//			String query = "insert into player(idplayer,username,idgame,playstatus,private_objectivecard_color) values('"
//					+ playerID + "','" + username + "', '" + gameID + "','" + playerStatus + "','" + playercolor
//					+ "');";
//			con.setPs(con.getCon().prepareStatement(query));
//			con.getPs().execute();
//			con.getCon().close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}

	public void challengeeOther(int playerID, String username, int gameID, String playerStatus, String playercolor) {
		new QueryDB().challengeeOther(playerID, username, gameID, playerStatus, playercolor);
	}

	// haalt private_objectivecard_color op uit de db.
//	public String getColor(String color) {
//		con.createConnection();
//		String result = "";
//		try {
//			String query = "select color from color where color = '" + color + "';";
//			con.setRs(con.getSt().executeQuery(query));
//			while (con.getRs().next()) {
//				result = con.getRs().getString("color");
//			}
//			con.getCon().close();
//		} catch (Exception e) {
//			System.out.println("e");
//		}
//		return result;
//	}

	public String getColor(String color) {
		return new QueryDB().getColor(color);
	}

	// haalt playerstatus op uit de db.
//	public String getPlayerStatus(String playerstatus) {
//		String result = "";
//		con.createConnection();
//		try {
//			String query = "SELECT playstatus from playstatus where playstatus = '" + playerstatus + "';";
//			con.setRs(con.getSt().executeQuery(query));
//			while (con.getRs().next()) {
//				result = con.getRs().getString("playstatus");
//			}
//			con.getCon().close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return result;
//	}

	public String getPlayerStatus(String playerstatus) {
		return new QueryDB().getPlayerStatus(playerstatus);
	}
}
