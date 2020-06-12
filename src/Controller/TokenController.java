package Controller;

import View.TokenPane;
import View.ToolCard;
import View.WindowPatternView;
import javafx.scene.layout.HBox;
import model.TokenModel;

public class TokenController {
	private TokenModel tokenModel;
	private GameController gameController;
	private WindowPatternView windowPatternView;
	private int tokenAmount;

	public TokenController(int tokenAmount, int gameID, int playerID, GameController gameController,
			WindowPatternView windowPatternView) {
		this.tokenAmount = tokenAmount;
		tokenModel = new TokenModel();
		this.gameController = gameController;
		this.windowPatternView = windowPatternView;
		setTokenController();

		if(tokenModel.tokensAdded(tokenAmount, playerID) == false) {
			for (int i = 0; i < tokenAmount; i++) {
				tokenModel.insertTokenInDB(gameID, playerID);

			}
		}
	}
	
	public void setTokenController() {
		gameController.setTokenController(this);
	}

	public void updateToken(int toolCardID, int roundID, int gameID, int playerID, int cost, int toolCardIndex) {
		tokenModel.updateToken(toolCardID, roundID, gameID, playerID, cost);
		WindowPatternView cardView = gameController.getGamePane().getOwnWindow();
		cardView.getTokens().getChildren().clear();
//		cardView.setTokens(this.getAddedTokenAmount(playerID), gameController.getPlayerController().getPlayerColor());
		for (int i = 0; i < this.getAddedTokenAmount(playerID); i++) {
			cardView.getTokens().getChildren().add(new TokenPane(gameController.getPlayerController().getPlayerColor()));
		}
		ToolCard toolCard = (ToolCard) gameController.getGamePane().getGamePaneBottom().getChildren()
				.get(toolCardIndex);
		HBox toolCardHBox = (HBox) toolCard.getChildren().get(1);
		for (int i = 0; i < cost; i++) {
			toolCardHBox.getChildren().add(new TokenPane(gameController.getPlayerController().getPlayerColor()));
		}

	}

	
	public int getToolCardCost(int toolCardID, int gameID) {
		return tokenModel.getToolCardCost(toolCardID, gameID);
	}
	public int getToolCardID(int gameID, String cardName) {
		return tokenModel.getToolCardID(gameID, cardName);
	}
	public int getTokenAmount() {
		return tokenAmount;
	}
	public int getAddedTokenAmount(int playerID) {
		return tokenModel.getTokenAmount(playerID);
	}

}
