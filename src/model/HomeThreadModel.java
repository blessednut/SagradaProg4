package model;

import java.util.ArrayList;

import DataBase.HomeThreadDB;

public class HomeThreadModel {
	private int gameID;
	private String username;
	private ArrayList<Integer> challengedGameID;

	public HomeThreadModel() {
		challengedGameID = new ArrayList<Integer>();
	}

	// methode om de gameID op te halen die de laatste creationdate heeft
	public int getGameID(String username) {
		return new HomeThreadDB(this).getGameID(username);
	}

	// methode om de username van de challenger te vinden die de laatste game heeft
	// aangemaakt

	// todo
	public String getUsernameOfChallenger(int gameid) {
		String username = new HomeThreadDB(this).getUsernameOfChallenger(gameid);
		return username;
		
	}

	public String getUsername() {
		return username;
	}

	public void addToArray(int GameID) {
			challengedGameID.add(GameID);
	}
	
	public ArrayList<Integer> getChallengedGameID() {
		return challengedGameID;
	}

}
