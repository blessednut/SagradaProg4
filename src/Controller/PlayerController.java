package Controller;

import model.PlayerModel;

public class PlayerController {
	private GameController gameController;
	private PatternCardController cardController;
	private PlayerModel playerModel;
	
	public PlayerController (GameController gameController, int gameID, String username, boolean isCurrentPlayer) {
		this.gameController = gameController;
		this.playerModel = new PlayerModel(gameID, username, isCurrentPlayer);
		this.cardController = new PatternCardController(this.gameController, this);
		this.cardController.loadChosenCard();
	}
	public PlayerController (GameController gameController, int gameID, String username, boolean isCurrentPlayer, boolean isOldGame) {
		this.gameController = gameController;
		this.playerModel = new PlayerModel(gameID, username, isCurrentPlayer);
		this.cardController = new PatternCardController(this.gameController, this);
	}
	public void loadCards() {
		this.cardController.loadChosenCard();
	}
	
	public PatternCardController getPatternCard () {
		return this.cardController;
	}
	
	public int getPlayerID () {
		return this.playerModel.getPlayerID();
	}
	
	public void updatePlayerFrameField () {
		this.cardController.updatePlayerFrameField();
	}
	
	public PlayerModel getPlayerModel () {
		return this.playerModel;
	}
}
