package Controller;

import java.util.ArrayList;

import View.DraftPoolView;
import View.GamePane;
import model.DiceModel;
import model.GameDiceModel;
import model.GameModel;

public class GameController {
	
	private MySceneController myScene;
	private GamePane gamePane;
	private GameModel m_game;
	private LogInController c_login;

	private Public_Objective_Card_Controller public_OCC;
	private Private_Objective_Card_Controller private_OCC;
	private ToolCard_Controller TCC;
	private ChatController CC;

	private PlayerController playerController;

	private DiceModel dice;
	private DraftpoolController draftpoolController;

	public GameController(MySceneController myScene, LogInController c_login) {
		this.myScene = myScene;
		this.c_login = c_login;
		this.m_game = new GameModel();

	}
	public void switchBackToHome() {
		myScene.getMyscene().switchPane(c_login.getC_home().getV_home());
		closeChatThread();
		
	}

	public void makePublicOC() {
		this.public_OCC = new Public_Objective_Card_Controller(this);
	}


	public Private_Objective_Card_Controller getPrivate_OCC() {
		this.private_OCC = new Private_Objective_Card_Controller(m_game.getGameId(), c_login.getUsername());
		return private_OCC;
	}
	public ToolCard_Controller getTCC() {
		return TCC;
	}
	public void createGamePane() {
		this.gamePane = new GamePane(this);
		myScene.getMyscene().switchPane(gamePane);

		this.dice = new DiceModel(this);
		this.playerController = new PlayerController(this, m_game.getGameId(), c_login.getUsername());

		this.draftpoolController = new DraftpoolController(this);

		this.draftpoolController.createDraftPool(4);
		gamePane.setDrafpool(new DraftPoolView(366, 366, draftpoolController.getDraftPool()));
	}

	public GameDiceModel pickDiceFromBag () {
		//Vraag om een lijst van dobbelstenen die nog in de zak zitten
		ArrayList<GameDiceModel> diceBag = this.dice.getBag();
		//Selecteer willekeurig een dice uit de zak
		GameDiceModel gameDice = diceBag.get(getRandomInt(0, diceBag.size() - 1));
		//Voeg de dice toe aan het diceModel
		this.dice.addDice(gameDice);
		//Return de geselecteerde dice
		return gameDice;
	}

	public GamePane getGamePane() {
		return gamePane;
	}

	public MySceneController getMyscene() {
		return myScene;
	}

	public PatternCardController getC_patternCard() {
		return this.playerController.getPatternCard();
	}

	public LogInController getC_login() {
		return c_login;
	}

	public GameModel getM_game() {
		return m_game;
	}

	public boolean placeDice (GameDiceModel dice) {
		return this.playerController.getPatternCard().placeDice(dice);
//		System.out.println("GameController:");
//		System.out.println(dice.getDieNumber());
	}

	private int getRandomInt(int min, int max) {
		return (int) Math.floor((Math.random() * ((max - min) + 1)) + min);
	}

	public void endTurn() {
		this.playerController.updatePlayerFrameField();
	}

	public DraftpoolController getDraftpoolController() {
		return draftpoolController;
	}

	public void makeTCC() {
		this.TCC = new ToolCard_Controller(this);
	}

	public Public_Objective_Card_Controller getPublic_OCC() {
		return public_OCC;
	}


	public PlayerController getPlayerController() {
		return playerController;
	}
	
	public ChatController makeCC() {
		return this.CC = new ChatController(this);		
	}
	
	public void closeChatThread() {
		CC.getThread().terminate();
		CC.getThread().getModel().getDBCOn().closeConnection();
	}
	

}
