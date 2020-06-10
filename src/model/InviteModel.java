package model;

import DataBase.InviteDB;

public class InviteModel {


	public InviteModel() {

	}

	// checkt of je de persoon al hebt uitgenodigd
	public boolean checkForDoubleInvite(String username, int idgame, String playstatus) {
		return new InviteDB().checkForDoubleInvite(username, idgame, playstatus);
	}

	// haalt username op uit db.
	public String checkInDatabase(String username) {
		return new InviteDB().checkInDatabase(username);
	}

	// haalt max(idplayer) op uit db en verhoodt dit met 1 en stuurd terug als int.
	public int maxPlayerId() {
		return new InviteDB().maxPlayerId();
	}

	// vult player table in de db voor eigen username.
	public void challengeeSelf(int playerID, String username, int gameID, String playerStatus, String playercolor) {
		new InviteDB().challengeeSelf(playerID, username, gameID, playerStatus, playercolor);
	}

	// vult player tabel in de db voor de uitgedaagde speler.
	public void challengeeOther(int playerID, String username, int gameID, String playerStatus, String playercolor) {
		new InviteDB().challengeeOther(playerID, username, gameID, playerStatus, playercolor);
	}

	// haalt private_objectivecard_color op uit de db.
	public String getColor(String color) {
		return new InviteDB().getColor(color);
	}

	// haalt playerstatus op uit de db.
	public String getPlayerStatus(String playerstatus) {
		return new InviteDB().getPlayerStatus(playerstatus);
	}

	public void updatePlayerStatusChallengee(String username, int gameid, String playstatus) {
		new InviteDB().updatePlayerStatusChallengee(username, gameid, playstatus);
	}
	public String getInviteGameID() {
		int result = new InviteDB().getInviteGameID();
		//System.out.println(result);
		return Integer.toString(result);
	}
	
	public int checkInvitation(String challenger, String challengee) {
		return new InviteDB().checkInvitation(challenger, challengee);
	}
}
