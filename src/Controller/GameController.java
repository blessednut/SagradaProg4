package Controller;

import View.DraftPoolView;
import View.GamePane;
import model.GameDiceModel;
import model.GameModel;

public class GameController {
	private MySceneController myScene;
	private GamePane gamePane;
	private PatternCardController c_patternCard;
	private GameModel m_game;
	private LogInController c_login;

	private Public_Objective_Card_Controller public_OCC;
	private Private_Objective_Card_Controller private_OCC;
	private ToolCard_Controller TCC;
	private DraftpoolController draftController;

	public GameController(MySceneController myScene, LogInController c_login) {
		this.myScene = myScene;
		this.c_login = c_login;

		this.m_game = new GameModel();
//		this.gamePane = new GamePane(this);


	}

	public Public_Objective_Card_Controller getPublic_OCC() {
		this.public_OCC = new Public_Objective_Card_Controller();
		return public_OCC;
	}

	public Private_Objective_Card_Controller getPrivate_OCC() {
		this.private_OCC = new Private_Objective_Card_Controller(m_game.getGameId(), c_login.getUsername());
		return private_OCC;
	}
	public ToolCard_Controller getTCC() {
		this.TCC = new ToolCard_Controller();
		return TCC;
	}
	public void createGamePane() {
		this.gamePane = new GamePane(this);
		myScene.getMyscene().switchPane(gamePane);
		c_patternCard = new PatternCardController(this);

		draftController = new DraftpoolController(this, 2);
		gamePane.setDrafpool(new DraftPoolView(366, 366, draftController.getDraftPool()));
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

	public void placeDice (GameDiceModel dice) {
		this.c_patternCard.placeDice(dice);
	}
}
