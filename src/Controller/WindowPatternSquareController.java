package Controller;

import View.WindowPatternSquareView;
import model.GameDiceModel;
import model.PatternCardFieldModel;

public class WindowPatternSquareController {
	private PatternCardController controller;
	private PatternCardFieldModel square;
	private GameDiceModel dice;
	private WindowPatternSquareView squareView;
	private ToolCard_Controller toolController;
	private GameController gameController;

	public WindowPatternSquareController(GameController gameController, PatternCardController controller, PatternCardFieldModel square) {
		this.gameController = gameController;
		this.controller = controller;
		this.square = square;
		toolController = null;
	}
	
	public void setView (WindowPatternSquareView squareView) {
		this.squareView = squareView;
	}

	public void onClick() {
		if (gameController.getIsTurn()) {
			if (toolController == null) {
				//isClicked = true;
				this.controller.setSelected(this);
			} else {
				toolController.setSquare(this, toolController.getActiveToolCard());
			}
		}
	}
	
	public void removeDice() {
		this.dice = null;
		this.square.removeDice();
		this.squareView.updateView();
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
	
	public void setToolCard (ToolCard_Controller toolController) {
		this.toolController = toolController;
	}
	
	public void removeToolCard () {
		this.toolController = null;
	}
}
