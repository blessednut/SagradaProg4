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
		v_invite = new InvitePane();
		m_invite = new InviteModel(this);
		v_invite.getName().setOnMouseClicked(e -> setSearchButton());
		v_invite.getSearch().setOnMouseClicked(e -> searchForUsername(v_invite.getName().getText()));
		v_invite.getInviteButton().setOnMouseClicked(e -> inVitePlayer());
	}
	
	public void inVitePlayer() {
		c_game.getM_game().createGameRoom();
		m_invite.challengeeSelf(m_invite.maxPlayerId(), home.getC_login().getUsername(), c_game.getM_game().getGameId(), m_invite.getPlayerStatus("challenger"), m_invite.getColor("blue"));
//		System.out.println("hallo");
		m_invite.challengeeOther(m_invite.maxPlayerId(), v_invite.getName().getText(), c_game.getM_game().getGameId(), m_invite.getPlayerStatus("challengee"), m_invite.getColor("green"));
		c_game.createGamePane();
	}
	
	public void searchForUsername(String username) {
		try {
			if(m_invite.checkInDatabase(username).equals(username)) {
				v_invite.getInviteButton().setVisible(true);
				v_invite.getSearch().setVisible(false);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void setSearchButton() {
		v_invite.getName().clear();
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
