package Controller;

import View.WindowPatternSquareView;
import model.GameDiceModel;
import model.PatternCardFieldModel;

public class WindowPatternSquareController {
	private PatternCardController patternCardController;
	private PatternCardFieldModel patternCardModel;
	private GameDiceModel diceModel;
	private WindowPatternSquareView squareView;
	private ToolCard_Controller toolController;
	private GameController gameController;

	public WindowPatternSquareController(GameController gameController, PatternCardController patternCardController, PatternCardFieldModel patternCardModel) {
		this.gameController = gameController;
		this.patternCardController = patternCardController;
		this.patternCardModel = patternCardModel;
		toolController = null;
	}
	
	public void setView (WindowPatternSquareView squareView) {
		this.squareView = squareView;
	}

	public void onClick() {
		if (gameController.getIsTurn()) {
			if (toolController == null) {
				//isClicked = true;
				this.patternCardController.setSelected(this);
			} else {
				toolController.setSquare(this, toolController.getActiveToolCard());
			}
		}
	}
	
	public void removeDice() {
		this.diceModel = null;
		this.patternCardModel.removeDice();
		this.squareView.updateView();
	}
	
	public void removeDiceFromView () {
		this.diceModel = null;
		this.squareView.updateView();
	}

	public void setDice(GameDiceModel dice) {
		this.diceModel = dice;
		squareView.setDice();
		patternCardModel.setDice(dice);
	}

	public GameDiceModel getDice() {
		return this.diceModel;
	}

	public PatternCardFieldModel getSquare() {
		return this.patternCardModel;
	}
	
	public void setToolCard (ToolCard_Controller toolController) {
		this.toolController = toolController;
	}
	
	public void removeToolCard () {
		this.toolController = null;
	}
}
