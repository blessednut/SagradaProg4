package Controller;

import View.OpenGamesPane;
import model.OpenGamesModel;

public class OpenGamesController {
	private OpenGamesPane openGameView;
	private OpenGamesModel openGamesModel;
	private GameController gameController;

	public OpenGamesController(LogInController logInController, GameController gameController) {
		openGamesModel = new OpenGamesModel();
		openGameView = new OpenGamesPane();
		openGameView.getOpenGame().setOnAction(e -> {openGame(); });
		this.gameController = gameController;

	}

	public OpenGamesPane getOGP() {
		return openGameView;
	}

	public OpenGamesModel getOGM() {
		return openGamesModel;
	}

	public void fillGames() {
		openGameView.getOldGamesBox().getItems().clear();
		for (int i = 0; i < openGamesModel.getOldGamesArray().size(); i++) {
			if (!openGameView.getOldGamesBox().getItems().contains(openGamesModel.getOldGamesArray().get(i))) {
				openGameView.getOldGamesBox().getItems().add("Spel ID: " + openGamesModel.getOldGamesArray().get(i)
						+ ",		Datum van spelcreatie: " + openGamesModel.getOldGamesDateArray().get(i));
			}
		}

	}

	public void fillAllGames() {
		for (int i = 0; i < openGamesModel.getOpenGameID().size(); i++) {
			if (!openGameView.getAllGames().getItems().contains(openGamesModel.getOpenGameID().get(i))) {
				openGameView.getAllGames().getItems().add("Spel ID: " + openGamesModel.getOpenGameID().get(i)
						+ ",      Datum van spelcreatie: " + openGamesModel.getOpenGameTime().get(i));
			}
		}
	}

	public int getSelectedGameID() {
		int gameId = 0;
		String[] parts = openGameView.getOldGamesBox().getValue().split(",");
		String[] idParts = parts[0].split(": ");
		String StringGameID = idParts[1];
		gameId = Integer.parseInt(StringGameID);
		return gameId;
	}

	public void openGame() {
		if (getSelectedGameID() != 0) {
			gameController.createGamePane(getSelectedGameID());
			
		}

	}

}
