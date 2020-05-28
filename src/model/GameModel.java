package model;

import DataBase.GameDB;
import DataBase.InGameDB;

public class GameModel {
	private int idgame;
	private InGameDB con;

	public GameModel() {
		con = new InGameDB(this);
	}

	// haalt max(idgame) op uit de db en verhoogd deze met 1 en insert deze vervolgens.
	public void createGameRoom() {
		this.idgame = new GameDB(this).createGameRoom();
	}

	public int getGameId() {
		return this.idgame;
	}
	public void setGameId(int GameId) {
		this.idgame = GameId;
	}

	public void updatePlayerTurn(int seqNR) {
		new GameDB(this).updatePlayerTurn(seqNR);
	}
	
	public void changeTurnPlayerID (int playerID) {
		this.con.changeTurnPlayerID(idgame, playerID);
	}
	
	public int getSeqNR (int playerID) {
		return this.con.getSeqNR(idgame, playerID);
	}
	
	public int getTurnPlayerID () {
		return this.con.getTurnPlayerID(idgame);
	}
	
	public int getRoundID () {
		return this.con.getRoundID(idgame);
	}
	
	public boolean getClockwise () {
		return this.con.getClockwise(idgame);
	}
	
	public int getRoundNR () {
		return this.con.getRoundNR(idgame);
	}
	
	public int getHighestSeqnr () {
		return this.con.getHighestSeqnr(idgame);
	}
	
	public void setRoundID () {
		this.con.setRoundID(idgame);
	}
	
	public int getPlayerID (int seqnr) {
		return this.con.getPlayerID(idgame, seqnr);
	}
	
	public void updateSeqNR (int playerID, int seqnr) {
		this.con.updateSeqNR(playerID, seqnr);
	} 
}
