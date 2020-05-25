package Controller;

import View.InvitePane;
import View.InviteStart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import model.InviteModel;

public class InviteController {

	private InvitePane invitePane;
	private InviteModel inviteModel;
	private GameController gameController;
	private HomeController home;
	private GameAcceptionThreadController gameAcceptionThread;
	private InviteStart inviteStart;
	private String StringGameID;

	private static final String ACCEPTED = "accepted";
	private static final String REFUSED = "refused";

	public InviteController(GameController gameController, HomeController home) {
		this.gameController = gameController;
		this.home = home;
		inviteModel = new InviteModel(this);
		invitePane = new InvitePane();

		// de switchcase bepaalt aan de hand van de selected RadioButton welke methode
		// er gekozen moet worden.
		invitePane.getSearch().setOnMouseClicked(e -> {
			switch (invitePane.getButtons().getSelectedToggle().getUserData().toString()) {
			case "twee":
				searchForUsername(invitePane.getName1().getText());
				break;
			case "drie":
				searchForTwoUsernames(invitePane.getName1().getText(), invitePane.getName2().getText());
				break;
			case "vier":
				searchForThreeUsernames(invitePane.getName1().getText(), invitePane.getName2().getText(),
						invitePane.getName3().getText());
				break;
			}
		});
		invitePane.getName1().setOnMouseClicked(e -> setSearchButton());
		invitePane.getInviteButton().setOnMouseClicked(e -> inVitePlayer());

		invitePane.getAccept().setOnMouseClicked(e -> {
			acceptInvitation(home.getC_login().getUsername());
			createInviteStartPane(StringGameID);
		});
		invitePane.getRefuse().setOnMouseClicked(e -> refuseInvitation(home.getC_login().getUsername()));

	}

	public void acceptInvitation(String username) {
		String[] parts = invitePane.getInvites().getValue().split("\\+");
		StringGameID = parts[1];

		int Gameid = Integer.parseInt(StringGameID);
		gameAcceptionThread = new GameAcceptionThreadController(home.getC_login(), this, Gameid);
		gameAcceptionThread.setDaemon(true);
		gameAcceptionThread.start();
		
		gameController.getM_game().setGameId(Gameid);

		inviteModel.updatePlayerStatusChallengee(username, Gameid, ACCEPTED);
		invitePane.getInvites().getItems().remove(invitePane.getInvites().getValue());

	}

	public void refuseInvitation(String username) {
		String[] parts = invitePane.getInvites().getValue().split("\\+");
		String StringGameID = parts[1];

		int Gameid = Integer.parseInt(StringGameID);
		gameController.getM_game().setGameId(Gameid);

		inviteModel.updatePlayerStatusChallengee(username, Gameid, REFUSED);
		invitePane.getInvites().getItems().remove(invitePane.getInvites().getValue());

		Alert exception = new Alert(AlertType.ERROR, "Je hebt de uitnodiging nu geweigerd", ButtonType.OK);
		exception.showAndWait();
	}

	public void inVitePlayer() {
		switch (invitePane.getButtons().getSelectedToggle().getUserData().toString()) {
		case "twee":
			gameController.getM_game().createGameRoom();
			inviteModel.challengeeSelf(inviteModel.maxPlayerId(), home.getC_login().getUsername(),
					gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challenger"),
					inviteModel.getColor("blue"));
			// uitgedaagde
			inviteModel.challengeeOther(inviteModel.maxPlayerId(), invitePane.getName1().getText(),
					gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challengee"),
					inviteModel.getColor("green"));
			gameController.createGamePane();
			break;
		case "drie":
			gameController.getM_game().createGameRoom();
			inviteModel.challengeeSelf(inviteModel.maxPlayerId(), home.getC_login().getUsername(),
					gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challenger"),
					inviteModel.getColor("blue"));
			// eerste uitgedaagde
			inviteModel.challengeeOther(inviteModel.maxPlayerId(), invitePane.getName1().getText(),
					gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challengee"),
					inviteModel.getColor("green"));
			// tweede uigedaagde
			inviteModel.challengeeOther(inviteModel.maxPlayerId(), invitePane.getName2().getText(),
					gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challengee"),
					inviteModel.getColor("purple"));
			gameController.createGamePane();
			break;
		case "vier":
			gameController.getM_game().createGameRoom();
			inviteModel.challengeeSelf(inviteModel.maxPlayerId(), home.getC_login().getUsername(),
					gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challenger"),
					inviteModel.getColor("blue"));
			// eerste uitgedaagde
			inviteModel.challengeeOther(inviteModel.maxPlayerId(), invitePane.getName1().getText(),
					gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challengee"),
					inviteModel.getColor("green"));
			inviteModel.challengeeOther(inviteModel.maxPlayerId(), invitePane.getName2().getText(),
					gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challengee"),
					inviteModel.getColor("purple"));
			inviteModel.challengeeOther(inviteModel.maxPlayerId(), invitePane.getName3().getText(),
					gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challengee"),
					inviteModel.getColor("red"));
			gameController.createGamePane();
			break;

		}
	}

	public void searchForUsername(String username) {
		try {
			if (inviteModel.checkInDatabase(username).equals(username)) {
				invitePane.getInviteButton().setVisible(true);
				invitePane.getSearch().setVisible(false);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void searchForTwoUsernames(String username1, String username2) {
		try {
			if (inviteModel.checkInDatabase(username1).equals(username1)
					&& inviteModel.checkInDatabase(username2).equals(username2)) {
				invitePane.getInviteButton().setVisible(true);
				invitePane.getSearch().setVisible(false);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void searchForThreeUsernames(String username1, String username2, String username3) {
		try {
			if (inviteModel.checkInDatabase(username1).equals(username1)
					&& inviteModel.checkInDatabase(username2).equals(username2)
					&& inviteModel.checkInDatabase(username3).equals(username3)) {
				invitePane.getInviteButton().setVisible(true);
				invitePane.getSearch().setVisible(false);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void createInviteStartPane(String gameID) {
		inviteStart = new InviteStart(gameID);
		home.addInviteStartPane(inviteStart);
		inviteStart.getStartGame().setOnMouseClicked(e -> {home.removeInviteStartPane(inviteStart);gameController.createGamePane();});
		
	}
	public void setStartButtonVisable() {
		inviteStart.getStartGame().setVisible(true);
	}

	public void setSearchButton() {
		invitePane.getName1().clear();
		invitePane.getInviteButton().setVisible(false);
		invitePane.getSearch().setVisible(true);
	}

	public InvitePane getV_InvitePane() {
		return invitePane;
	}

	public GameController getC_game() {
		return gameController;
	}

	public HomeController getHome() {
		return home;
	}

	public InviteStart getInviteStart() {
		return inviteStart;
	}
	
	
	
	

}
