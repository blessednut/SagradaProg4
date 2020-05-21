package model;

import DataBase.GameAcceptionThreadDB;

public class GameAcceptionThreadModel {
	
	public GameAcceptionThreadModel() {
		
	}
	
	public int getGameID(int gameID) {
		return new GameAcceptionThreadDB(this).getAmountInvitited(gameID);
	}
	
	public int getAmountAccepted(int gameID) {
		return new GameAcceptionThreadDB(this).getAmountAccepted(gameID);
	}
	
	public int getAmountRefused(int gameID) {
		return new GameAcceptionThreadDB(this).getAmountRefused(gameID);
	}
	
	public void setRefused(int gameid) {
		new GameAcceptionThreadDB(this).setRefused(gameid);
	}

}
