package model;

import DataBase.OpenGamesDB;

public class OpenGamesModel {
	private OpenGamesDB openGamesDB;
	
	public OpenGamesModel() {
		this.openGamesDB = new OpenGamesDB(this);
	}
	
	public int GetOpenGameID(String username) {
		return this.openGamesDB.GetOpenGameID(username);
	}

}
