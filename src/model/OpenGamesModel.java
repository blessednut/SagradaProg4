package model;

import java.sql.Date;
import java.util.ArrayList;

import DataBase.OpenGamesDB;

public class OpenGamesModel {
	private OpenGamesDB openGamesDB;
	private ArrayList<String> oldGamesIDArray;
	private ArrayList<Date> oldGamesDateArray;
	
	public OpenGamesModel() {
		this.openGamesDB = new OpenGamesDB(this);
		oldGamesIDArray = new ArrayList<String>();
		oldGamesDateArray = new ArrayList<Date>();
	}
	
	public int GetOpenGameID(String username) {
		return this.openGamesDB.GetOpenGameID(username);
	}
	
	public void fillOldGames(String gameIDString, Date date) {
		if(!oldGamesIDArray.contains(gameIDString)) {
			oldGamesIDArray.add(gameIDString);
			oldGamesDateArray.add(date);
		}
	}
	
	public ArrayList<String> getOldGamesArray(){
		return oldGamesIDArray;
	}
	
	public ArrayList<Date> getOldGamesDateArray(){
		return oldGamesDateArray;
	}

}
