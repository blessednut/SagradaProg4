package Controller;

import DataBase.DBCon;
import View.GamePane;
import model.GameModel;

public class GameController {
	private MySceneController myScene;
	private GamePane gamePane;
	private PatternCardController c_patternCard;
	private GameModel m_game;

	public GameController(MySceneController myScene) {
		this.myScene = myScene;
		this.gamePane = new GamePane(this);
		this.m_game = new GameModel();
		m_game.creatGameRoom();
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
}
