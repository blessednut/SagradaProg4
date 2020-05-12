package Controller;

import DataBase.WindowPatternDB;
import View.WindowPatternView;
import View.WindowPatternSquareView;
import model.GameDiceModel;
import model.PatternCardFieldModel;
import model.PatternCardModel;

public class PatternCardController {
	private GameController c_game;
	private WindowPatternSquareController[][] fieldController;
	private WindowPatternSquareController selectedSquare = null;
	
	//TODO: CONNECTIE WEGHALEN
	private WindowPatternDB con;

	public PatternCardController(GameController c_game) {
		this.c_game = c_game;
		this.con = new WindowPatternDB();
		generatePatternCardChoice();
	}
	
	public void placeDice (GameDiceModel dice) {
		if (selectedSquare != null) {
			selectedSquare.setDice(dice);
			System.out.println("Dice is geplaatst!");
		}
	}

	private void generatePatternCardChoice() {
		this.c_game.getGamePane().createChoicePane(makeRandomCard(getRandomIntBetweenRange(1, 24)),
				makeRandomCard(getRandomIntBetweenRange(1, 24)), makeRandomCard(getRandomIntBetweenRange(1, 24)),
				makeRandomCard(getRandomIntBetweenRange(1, 24)));
	}

	private WindowPatternSquareController[][] makeSquareView(PatternCardFieldModel[][] field) {
		//WindowPatternSquareView[][] fieldView = new WindowPatternSquareView[5][4];
		fieldController = new WindowPatternSquareController[5][4];
		
		for (int x = 0; x < fieldController.length; x++) {
			for (int y = 0; y < fieldController[x].length; y++) {
				fieldController[x][y] = new WindowPatternSquareController(this, field[x][y]);
			}
		}
		return fieldController;
	}

	private WindowPatternView makeRandomCard(int cardId) {
		PatternCardModel card = con.getPatternCard(cardId);
		PatternCardFieldModel[][] field = con.getField(cardId);
		return new WindowPatternView(450, 300, card.nameProperty(), card.tokenAmount(), makeSquareView(field));
	}

	public int getRandomIntBetweenRange(int min, int max) {
		return (int) ((Math.random() * ((max - min) + 1)) + min);
	}

	public void setChosenCard(WindowPatternView chosenCard) {
		this.c_game.getGamePane().setOwnWindow(chosenCard);
		this.c_game.getGamePane().createGamePane();
	}

	public void setSelected(WindowPatternSquareController selectedSquare) {
		this.selectedSquare = selectedSquare;
	}
}