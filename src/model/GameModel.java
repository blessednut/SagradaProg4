package model;

import DataBase.DBCon;
import DataBase.QueryDB;

public class GameModel {
	private int GameId;

	public GameModel() {
	}

	// haalt max(idgame) op uit de db en verhoogd deze met 1 en insert deze vervolgens.
	public void createGameRoom() {
		this.GameId =  new QueryDB().createGameRoom();
	}

	public int getGameId() {
		return this.GameId;
	}
	
	
	

}
