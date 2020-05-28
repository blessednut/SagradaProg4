package model;

import DataBase.PlayerDB;

public class PlayerModel {
	private int gameID;
	private String username;
	private int playerID;
	private boolean isTurn;

	private PlayerDB con;

	public PlayerModel(int gameID, String username) {
		this.con = new PlayerDB();
		this.gameID = gameID;
		this.username = username;
		this.playerID = importPlayerID();
		
		System.out.println("PlayerModel:");
		System.out.println("Player ID = " + this.playerID);
		System.out.println("Game ID = " + this.gameID);
		System.out.println("Username = " + this.username);
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

//	private String username = null;
//	private int currentGameID;
//
//	public PlayerModel() {
//
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public int getCurrentGameID() {
//		return currentGameID;
//	}
//
//	public void setCurrentGameID(int currentGameID) {
//		this.currentGameID = currentGameID;
//	}
}
