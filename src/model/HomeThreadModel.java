package model;

import java.util.ArrayList;

import DataBase.HomeThreadDB;

public class HomeThreadModel {
	private int gameID;
	private String username;
	private ArrayList<Integer> challengedGameID;
	private HomeThreadDB homeThreadDB;

	public HomeThreadModel() {
		
		this.homeThreadDB = new HomeThreadDB(this);
		challengedGameID = new ArrayList<Integer>();
	}

	// methode om de gameID op te halen die de laatste creationdate heeft
	public int getGameID(String username) {
		return homeThreadDB.getGameID(username);
	}

	// methode om de username van de challenger te vinden die de laatste game heeft
	// aangemaakt

	// todo
	public String getUsernameOfChallenger(int gameid) {
		String username = homeThreadDB.getUsernameOfChallenger(gameid);
		return username;
		
	}

	public String getUsername() {
		return username;
	}

	public void addToArray(int GameID) {
		if(!challengedGameID.contains(GameID)) {
			challengedGameID.add(GameID);
		}
	}

	public ArrayList<Integer> getChallengedGameID() {
		return challengedGameID;
	}

	public HomeThreadDB getHomeThreadDB() {
		return homeThreadDB;
	}
	

}
