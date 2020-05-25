package model;

import Controller.InviteController;
import DataBase.InviteDB;

public class InviteModel {
	private InviteController c_invite;

	public InviteModel(InviteController c_invite) {
		this.c_invite = c_invite;
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
}