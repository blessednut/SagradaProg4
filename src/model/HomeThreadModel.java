package model;

import DataBase.HomeThreadDB;

public class HomeThreadModel {
	private int gameID;
	private String username;
	
	public HomeThreadModel() {
	}
	
	// methode om de gameID op te halen die de laatste creationdate heeft
	public int getGameID(String playstatus) {
		return new HomeThreadDB().getGameID();
	}
	
	//methode om de username van de challenger te vinden die de laatste game heeft aangemaakt
	public String getUsernameOfChallenger(String playstatus) {
		return new HomeThreadDB().getUsernameOfChallenger(playstatus);
	}
	
	public String getUsername() {
		return username;
	}
	
}
