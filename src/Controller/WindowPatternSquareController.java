package Controller;

import View.WindowPatternSquareView;
import model.GameDiceModel;
import model.PatternCardFieldModel;

public class WindowPatternSquareController {
	private PatternCardController controller;
	private PatternCardFieldModel square;
	private GameDiceModel dice;
	private WindowPatternSquareView squareView;

	public WindowPatternSquareController(PatternCardController controller, PatternCardFieldModel square) {
		this.controller = controller;
		this.square = square;
	}
	
	public void setView (WindowPatternSquareView squareView) {
		this.squareView = squareView;
	}

	public void onClick() {
		this.controller.setSelected(this);
	}

	public void setDice(GameDiceModel dice) {
		this.dice = dice;
		squareView.setDice();
		square.setDice(dice);
	}

	public GameDiceModel getDice() {
		return this.dice;
	}

	public PatternCardFieldModel getSquare() {
		return this.square;
	}
}
