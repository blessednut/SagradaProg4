package model;

import DataBase.PlayerDB;

public class PlayerModel {
	private int gameID;
	private String username;
	private int playerID;
	private boolean isTurn;
	private boolean isCurrentPlayer;

	private PlayerDB con;

	public PlayerModel(int gameID, String username, boolean isCurrentPlayer) {
		this.con = new PlayerDB();
		this.gameID = gameID;
		this.username = username;
		this.playerID = importPlayerID();
		this.isCurrentPlayer = isCurrentPlayer;
		
	}

	private int importPlayerID() {
		return con.getPlayerID(this.gameID, this.username);
	}
	
	public int getPlayerID () {
		return this.playerID;
	}
	
	public boolean getTurn () {
		return this.isTurn;
	}
	
	public void setTurn (boolean isTurn) {
		this.isTurn = isTurn;
	}
	
	public boolean patterncardExists () {
		return con.patterncardExists(playerID);
	}
	
	public int getPatterncardID () {
		return con.getPatterncardID(playerID);
	}
	
	public boolean getIsCurrentPlayer () {
		return this.isCurrentPlayer;
	}

}
