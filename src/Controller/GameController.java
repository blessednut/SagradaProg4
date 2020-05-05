package Controller;

import View.GamePane;
import model.GameModel;

public class GameController {
	private MySceneController myScene;
	private GamePane gamePane;
	private PatternCardController c_patternCard;
	private GameModel m_game;
	private LogInController c_login;
	private PlayerController c_player;
	private DraftpoolController draftController;

	public GameController(MySceneController myScene, LogInController c_login) {
		this.myScene = myScene;
		this.c_login = c_login;
		this.c_player = new PlayerController(this);
		this.gamePane = new GamePane(this);
		this.m_game = new GameModel();

	}
	public void createGamePane() {
//		m_game.creatGameRoom();
		myScene.getMyscene().switchPane(gamePane);
		c_patternCard = new PatternCardController(this);
		
		draftController = new DraftpoolController(4);
		
	}

	public GamePane getGamePane() {
		return gamePane;
	}

	public MySceneController getMyscene() {
		return myScene;
	}

	public PatternCardController getC_patternCard() {
		return c_patternCard;
	}

	public LogInController getC_login() {
		return c_login;
	}

	public GameModel getM_game() {
		return m_game;
	}
	
	
	
	

}
