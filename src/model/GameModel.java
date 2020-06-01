package model;

import java.util.ArrayList;

import DataBase.GameDB;
import DataBase.InGameDB;

public class GameModel {
	private int idGame;
	private InGameDB inGameDB;

	public GameModel() {
		inGameDB = new InGameDB(this);
	}

	// haalt max(idgame) op uit de db en verhoogd deze met 1 en insert deze vervolgens.
	public void createGameRoom() {
		this.idGame = new GameDB(this).createGameRoom();
	}

	public int getGameId() {
		return this.idGame;
	}
	public void setGameId(int GameId) {
		this.idGame = GameId;
	}

	public void updatePlayerTurn(int seqNR) {
		new GameDB(this).updatePlayerTurn(seqNR);
	}
	
	public void changeTurnPlayerID (int playerID) {
		this.inGameDB.changeTurnPlayerID(idGame, playerID);
	}
	
	public int getSeqNR (int playerID) {
		return this.inGameDB.getSeqNR(idGame, playerID);
	}
	
	public int getTurnPlayerID () {
		return this.inGameDB.getTurnPlayerID(idGame);
	}
	
	public int getRoundID () {
		return this.inGameDB.getRoundID(idGame);
	}
	
	public boolean getClockwise () {
		return this.inGameDB.getClockwise(idGame);
	}
	
	public int getRoundNR () {
		return this.inGameDB.getRoundNR(idGame);
	}
	
	public int getHighestSeqnr () {
		return this.inGameDB.getHighestSeqnr(idGame);
	}
	
	public void setRoundID () {
		this.inGameDB.setRoundID(idGame);
	}
	
	public int getPlayerID (int seqnr) {
		return this.inGameDB.getPlayerID(idGame, seqnr);
	}
	
	public void updateSeqNR (int playerID, int seqnr) {
		this.inGameDB.updateSeqNR(playerID, seqnr);
	}
	
	public ArrayList<String> getOpponentNames (int playerID) {
		return this.inGameDB.getOpponentNames(idGame, playerID);
	}
	
	public String getPlayerName (int playerID) {
		return this.inGameDB.getPlayerID(playerID);
	}
	
	public void updateScore (int idplayer, int score) {
		this.inGameDB.updateScore(idplayer, score);
	}
	
	public void setPlayStatusFinished (int idplayer) {
		this.inGameDB.setPlayStatusFinished(idplayer);;
	}
	
	public String getWinner () {
		return this.inGameDB.getWinner(idGame);
	}
	
	public boolean gameEnded () {
		return this.inGameDB.gameEnded(idGame);
	}
}
