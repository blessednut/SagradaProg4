package Controller;

import View.InvitePane;
import model.InviteModel;

public class InviteController {

	private InvitePane v_invite;
	private InviteModel m_invite;
	private GameController c_game;
	private HomeController home;

	public InviteController(GameController c_game, HomeController home) {
		this.c_game = c_game;
		this.home = home;
		m_invite = new InviteModel(this);
		v_invite = new InvitePane();

		// de switchcase bepaalt aan de hand van de selected RadioButton welke methode
		// er gekozen moet worden.
		v_invite.getSearch().setOnMouseClicked(e -> {
			switch (v_invite.getButtons().getSelectedToggle().getUserData().toString()) {
			case "twee":
				searchForUsername(v_invite.getName1().getText());
				break;
			case "drie":
				searchForTwoUsernames(v_invite.getName1().getText(), v_invite.getName2().getText());
				break;
			case "vier":
				searchForThreeUsernames(v_invite.getName1().getText(), v_invite.getName2().getText(),
						v_invite.getName3().getText());
				break;
			}
		});
		v_invite.getName1().setOnMouseClicked(e -> setSearchButton());

		v_invite.getInviteButton().setOnMouseClicked(e -> inVitePlayer());
		
		//invitation part.
		

	}

	public void inVitePlayer() {
		switch (v_invite.getButtons().getSelectedToggle().getUserData().toString()) {
		case "twee":
			 c_game.getM_game().createGameRoom();
			m_invite.challengeeSelf(m_invite.maxPlayerId(), home.getC_login().getUsername(),
					c_game.getM_game().getGameId(), m_invite.getPlayerStatus("challenger"), m_invite.getColor("blue"));
			// uitgedaagde
			m_invite.challengeeOther(m_invite.maxPlayerId(), v_invite.getName1().getText(),
					c_game.getM_game().getGameId(), m_invite.getPlayerStatus("challengee"), m_invite.getColor("green"));
			c_game.createGamePane();
			break;
		case "drie":
			c_game.getM_game().createGameRoom();
			m_invite.challengeeSelf(m_invite.maxPlayerId(), home.getC_login().getUsername(),
					c_game.getM_game().getGameId(), m_invite.getPlayerStatus("challenger"), m_invite.getColor("blue"));
			// eerste uitgedaagde
			m_invite.challengeeOther(m_invite.maxPlayerId(), v_invite.getName1().getText(),
					c_game.getM_game().getGameId(), m_invite.getPlayerStatus("challengee"), m_invite.getColor("green"));
			// tweede uigedaagde
			m_invite.challengeeOther(m_invite.maxPlayerId(), v_invite.getName2().getText(),
					c_game.getM_game().getGameId(), m_invite.getPlayerStatus("challengee"),	m_invite.getColor("purple"));
			c_game.createGamePane();
			break;
		case "vier":
			c_game.getM_game().createGameRoom();
			m_invite.challengeeSelf(m_invite.maxPlayerId(), home.getC_login().getUsername(),
					c_game.getM_game().getGameId(), m_invite.getPlayerStatus("challenger"), m_invite.getColor("blue"));
			// eerste uitgedaagde
			m_invite.challengeeOther(m_invite.maxPlayerId(), v_invite.getName1().getText(),
					c_game.getM_game().getGameId(), m_invite.getPlayerStatus("challengee"), m_invite.getColor("green"));
			m_invite.challengeeOther(m_invite.maxPlayerId(), v_invite.getName2().getText(),
					c_game.getM_game().getGameId(), m_invite.getPlayerStatus("challengee"),	m_invite.getColor("purple"));
			m_invite.challengeeOther(m_invite.maxPlayerId(), v_invite.getName3().getText(),
					c_game.getM_game().getGameId(), m_invite.getPlayerStatus("challengee"), m_invite.getColor("red"));
			c_game.createGamePane();
			break;

		}
	}

	public void searchForUsername(String username) {
		try {
			if (m_invite.checkInDatabase(username).equals(username)) {
				v_invite.getInviteButton().setVisible(true);
				v_invite.getSearch().setVisible(false);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void searchForTwoUsernames(String username1, String username2) {
		try {
			if (m_invite.checkInDatabase(username1).equals(username1)
					&& m_invite.checkInDatabase(username2).equals(username2)) {
				v_invite.getInviteButton().setVisible(true);
				v_invite.getSearch().setVisible(false);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void searchForThreeUsernames(String username1, String username2, String username3) {
		try {
			if (m_invite.checkInDatabase(username1).equals(username1)
					&& m_invite.checkInDatabase(username2).equals(username2)
					&& m_invite.checkInDatabase(username3).equals(username3)) {
				v_invite.getInviteButton().setVisible(true);
				v_invite.getSearch().setVisible(false);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void setSearchButton() {
		v_invite.getName1().clear();
		v_invite.getInviteButton().setVisible(false);
		v_invite.getSearch().setVisible(true);
	}

	public InvitePane getV_InvitePane() {
		return v_invite;
	}

	public GameController getC_game() {
		return c_game;
	}

}
