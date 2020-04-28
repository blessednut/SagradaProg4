package Controller;

import DataBase.DBCon;
import View.WindowPatternView2;
import View.squareView;
import model.PatternCardFieldModel;
import model.PatternCardModel;

public class PatternCardController {

	private GameController c_game;
	private DBCon con;

	public PatternCardController(GameController c_game) {
		this.c_game = c_game;
		this.con = new DBCon();
		generatePatternCardChoice();
	}

	private void generatePatternCardChoice() {
		this.c_game.getGamePane().createChoicePane(makeRandomCard(getRandomIntBetweenRange(1, 24)),
				makeRandomCard(getRandomIntBetweenRange(1, 24)), makeRandomCard(getRandomIntBetweenRange(1, 24)),
				makeRandomCard(getRandomIntBetweenRange(1, 24)));
	}

	private squareView[][] makeSquareView(PatternCardFieldModel[][] field) {
		squareView[][] fieldView = new squareView[5][4];
		for (int x = 0; x < fieldView.length; x++) {
			for (int y = 0; y < fieldView[x].length; y++) {
				fieldView[x][y] = new squareView(field[x][y].colorProperty(), field[x][y].valueProperty(), null);
			}
		}
		return fieldView;
	}

	private WindowPatternView2 makeRandomCard(int cardId) {
		PatternCardModel card = con.getPatternCard(cardId);
		PatternCardFieldModel[][] field = con.getField(cardId);
		return new WindowPatternView2(450, 300, card.nameProperty(), card.tokenAmount(), makeSquareView(field));
	}

	public int getRandomIntBetweenRange(int min, int max) {
		return (int) ((Math.random() * ((max - min) + 1)) + min);
	}

	public void setChosenCard(WindowPatternView2 chosenCard) {
		this.c_game.getGamePane().setOwnWindow(chosenCard);
		this.c_game.getGamePane().createGamePane();
	}
}
