package Controller;

import View.WindowPatternView;
import model.GameDiceModel;
import model.PatternCardFieldModel;
import model.PatternCardModel;

public class PatternCardController {
	private GameController c_game;
	private WindowPatternSquareController[][] fieldController;
	private WindowPatternSquareController selectedSquare = null;
	private PatternCardModel chosenCard;
	private PatternCardModel[] optionCard;

	public PatternCardController(GameController c_game) {
		this.c_game = c_game;
		generatePatternCardChoice();
	}
	
	public void placeDice (GameDiceModel dice) {
		if (selectedSquare != null) {
			selectedSquare.setDice(dice);
			System.out.println("Dice is geplaatst!");
		}
	}

	private void generatePatternCardChoice() {
		this.optionCard = new PatternCardModel[4];
		
		//Check hier voor mogelijke dubbele patterncards
		for (int i = 0; i < optionCard.length; i++) {
			this.optionCard[i] = new PatternCardModel(getRandomIntBetweenRange(1, 24));
		}
		
		this.c_game.getGamePane().createChoicePane(makeView(0), makeView(1), makeView(2), makeView(3));
	}

	private WindowPatternSquareController[][] makeSquareView(PatternCardFieldModel[][] field) {
		fieldController = new WindowPatternSquareController[5][4];
		
		for (int x = 0; x < fieldController.length; x++) {
			for (int y = 0; y < fieldController[x].length; y++) {
				fieldController[x][y] = new WindowPatternSquareController(this, field[x][y]);
			}
		}
		return fieldController;
	}
	
	private WindowPatternView makeView (int index) {
		//Magic Number weghalen
		//Property weghalen uit model
		return new WindowPatternView(450, 300, optionCard[index].nameProperty(), optionCard[index].tokenAmount(), makeSquareView(optionCard[index].getField()));
	}
	
	private WindowPatternView makeView (PatternCardModel card) {
		return new WindowPatternView(450, 300, card.nameProperty(), card.tokenAmount(), makeSquareView(card.getField()));
	}

	public int getRandomIntBetweenRange(int min, int max) {
		return (int) ((Math.random() * ((max - min) + 1)) + min);
	}

	public void setChosenCard(int index) {
		this.chosenCard = this.optionCard[index];
		this.c_game.getGamePane().setOwnWindow(makeView(chosenCard));
		this.c_game.getGamePane().createGamePane();
	}

	public void setSelected(WindowPatternSquareController selectedSquare) {
		this.selectedSquare = selectedSquare;
	}
}