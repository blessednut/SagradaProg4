package model;

import DataBase.GameAcceptionThreadDB;

public class GameAcceptionThreadModel {
	private GameAcceptionThreadDB gameAcceptionThreadDB;

	public GameAcceptionThreadModel() {
		this.gameAcceptionThreadDB = new GameAcceptionThreadDB(this);
	}

	public int getGameID(int gameID) {
		return gameAcceptionThreadDB.getAmountInvitited(gameID);
	}

	public int getAmountAccepted(int gameID) {
		return gameAcceptionThreadDB.getAmountAccepted(gameID);
	}

	public int getAmountRefused(int gameID) {
		return gameAcceptionThreadDB.getAmountRefused(gameID);
	}

	public void setRefused(int gameid) {
		gameAcceptionThreadDB.setRefused(gameid);
	}

	public GameAcceptionThreadDB getGameAcceptionThreadDB() {
		return gameAcceptionThreadDB;
	}



}
