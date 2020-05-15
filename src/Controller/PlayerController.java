package Controller;

import model.PlayerModel;

public class PlayerController {
	private GameController gameController;
	private PatternCardController cardController;
	private PlayerModel playerModel;
	
	public PlayerController (GameController gameController, int gameID, String username) {
		this.gameController = gameController;
		this.cardController = new PatternCardController(this.gameController, this);
		this.playerModel = new PlayerModel(gameID, username);
	}
	
	public PatternCardController getPatternCard () {
		return this.cardController;
	}
	
	public int getPlayerID () {
		return this.playerModel.getPlayerID();
	}
}
