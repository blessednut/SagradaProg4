package model;

import java.sql.Timestamp;
import java.util.ArrayList;

import DataBase.OpenGamesDB;

public class OpenGamesModel {
	private OpenGamesDB openGamesDB;
	private ArrayList<String> oldGamesIDArray;
	private ArrayList<Timestamp> oldGamesDateArray;
	
	private ArrayList<String> openGameID;
	private ArrayList<Timestamp> openGameTime;
	private ArrayList<String> ownOpenGameID;
	
	public OpenGamesModel() {
		this.openGamesDB = new OpenGamesDB(this);
		oldGamesIDArray = new ArrayList<String>();
		oldGamesDateArray = new ArrayList<Timestamp>();
		// voor de lijst game
		openGameID = new ArrayList<String>();
		ownOpenGameID = new ArrayList<String>();
		
		openGameTime = new ArrayList<Timestamp>();
	}
	
	public int GetOpenGameID(String username) {
		return this.openGamesDB.GetOpenGameID(username);
	}
	
	public void fillOldGames(String gameIDString, Timestamp date) {
		if(!oldGamesIDArray.contains(gameIDString)) {
			oldGamesIDArray.add(gameIDString);
			oldGamesDateArray.add(date);
		}
	}
	
	public void getGamesIDS() {
		this.openGamesDB.getgameIDs();
	}
	
	public void fillArrayOwnGames(String gameIDString) {
		if(!ownOpenGameID.contains(gameIDString)) {
			ownOpenGameID.add(gameIDString);
		}
	}
	
	public void fillArrayForGames(String gameIDString, Timestamp creationdate) {
		if(!openGameID.contains(gameIDString)) {
			openGameID.add(gameIDString);
			openGameTime.add(creationdate);
		}
	}
	
	
	public String getOwnGamesID(String username) {
		return this.openGamesDB.getOwnGamesID(username);
	}
	
	public ArrayList<String> getOldGamesArray(){
		return oldGamesIDArray;
	}
	
	public ArrayList<Timestamp> getOldGamesDateArray(){
		return oldGamesDateArray;
	}

	public ArrayList<String> getOpenGameID() {
		return openGameID;
	}

	public ArrayList<Timestamp> getOpenGameTime() {
		return openGameTime;
	}

	public ArrayList<String> getOwnOpenGameID() {
		return ownOpenGameID;
	}


}
