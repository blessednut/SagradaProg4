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
	private Public_Objective_Card_Controller public_OCC;
	private Private_Objective_Card_Controller private_OCC;
	private ToolCard_Controller TCC;

	public GameController(MySceneController myScene, LogInController c_login) {
		this.myScene = myScene;
		this.c_login = c_login;
		this.c_player = new PlayerController(this);
		this.public_OCC = new Public_Objective_Card_Controller();
//		TODO: playerID veriabelen meegeven aan de constructor.
		this.private_OCC = new Private_Objective_Card_Controller("private_geel");
		this.TCC = new ToolCard_Controller();
		this.gamePane = new GamePane(this);
		this.m_game = new GameModel();


		

	}
	public Public_Objective_Card_Controller getPublic_OCC() {
		return public_OCC;
	}
	public Private_Objective_Card_Controller getPrivate_OCC() {
		return private_OCC;
	}
	public ToolCard_Controller getTCC() {
		return TCC;
	}
	public void createGamePane() {
		myScene.getMyscene().switchPane(gamePane);
		c_patternCard = new PatternCardController(this);
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
