package Controller;

import DataBase.DBCon;
import View.GamePane;
import View.WindowPatternView2;
import View.squareView;
import model.PatternCardFieldModel;
import model.PatternCardModel;

public class GameController {
	private MySceneController myScene;
	private GamePane gamePane;
//	private WindowPatternView2 chosenCard;
	private PatternCardController c_patternCard;
	private DBCon con;

	//Krijg dbCon mee met de constructor
	public GameController(MySceneController myScene) {
		this.myScene = myScene;
		this.gamePane = new GamePane(this);
		myScene.getMyscene().switchPane(gamePane);
		con = new DBCon();
		c_patternCard = new PatternCardController(this);

//		generatePatternCardChoice();
	}
	
	

	public GamePane getGamePane() {
		return gamePane;
	}



//	private void generatePatternCardChoice() {
//		this.gamePane.createChoicePane(makeRandomCard(getRandomIntBetweenRange(1, 24)),
//				makeRandomCard(getRandomIntBetweenRange(1, 24)), makeRandomCard(getRandomIntBetweenRange(1, 24)),
//				makeRandomCard(getRandomIntBetweenRange(1, 24)));
//	}
//
//	private squareView[][] makeSquareView(PatternCardFieldModel[][] field) {
//		squareView[][] fieldView = new squareView[5][4];
//		for (int x = 0; x < fieldView.length; x++) {
//			for (int y = 0; y < fieldView[x].length; y++) {
//				fieldView[x][y] = new squareView(field[x][y].colorProperty(), field[x][y].valueProperty(), null);
//				System.out.println(field[x][y].colorProperty().getValue());
//			}
//		}
//		return fieldView;
//	}
//
//	private WindowPatternView2 makeRandomCard(int cardId) {
//		PatternCardModel card = con.getPatternCard(cardId);
//		//PatternCardModel card = new PatternCardModel(cardId);
//		PatternCardFieldModel[][] field = con.getField(cardId);
//		return new WindowPatternView2(450, 300, card.nameProperty(), card.tokenAmount(), makeSquareView(field));
//	}
//
	public MySceneController getMyscene() {
		return myScene;
	}
//
//	public int getRandomIntBetweenRange(int min, int max) {
//		return (int) ((Math.random() * ((max - min) + 1)) + min);
//	}
//	
//	public void setChosenCard (WindowPatternView2 chosenCard) {
//		this.chosenCard = chosenCard;
//		gamePane.setOwnWindow(chosenCard);
//		gamePane.createGamePane();
//	}



	public PatternCardController getC_patternCard() {
		return c_patternCard;
	}
	
	
}
