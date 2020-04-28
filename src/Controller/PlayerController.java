package Controller;

import model.PlayerModel;

public class PlayerController {
	
	private GameController c_game;
	private PlayerModel m_player;
	
	public PlayerController(GameController c_game) {
		this.c_game = c_game;
		m_player = new PlayerModel();
		m_player.setUsername(c_game.getC_login().SetInlogInfo());
	}
	
	public void setGameID() {
		m_player.setCurrentGameID(c_game.getM_game().getGameId());
	}

}
