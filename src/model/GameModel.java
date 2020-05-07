package model;

import DataBase.GameDB;

public class GameModel {
	private int GameId;

	public GameModel() {
	}

	// haalt max(idgame) op uit de db en verhoogd deze met 1 en insert deze vervolgens.
	public void createGameRoom() {
		this.GameId = new GameDB().createGameRoom();
	}

	public int getGameId() {
		return this.GameId;
	}
}
