package Controller;

import View.DraftPoolSquareView;
import model.GameDiceModel;

public class DraftpoolSquareController {
	private GameController gameController;
	private DraftpoolController controller;
	private DraftPoolSquareView view;
	private GameDiceModel dice;
	
	public DraftpoolSquareController (GameController gameController, DraftpoolController controller, GameDiceModel dice) {
		this.gameController = gameController;
		this.controller = controller;
		this.dice = dice;
	}
	
	public DraftpoolSquareController () {
		this.dice = null;
	}
	
	public void onClick () {
		if (gameController.getIsTurn()) {
			if (dice != null) {
				//System.out.println("Dice color = " + dice.colorProperty().getValue() + " eyes = " + dice.valueProperty().getValue());
				controller.setSelectedDice(dice, this);
			}
		}
	}
	
	public GameDiceModel getDice () {
		return this.dice;
	}
	
	public void removeDice () {
		this.dice = null;
		this.updateView();
	}
	
	public void setView (DraftPoolSquareView view) {
		this.view = view;
	}
	
	public void updateView () {
		this.view.updateView();
	}

	public void setDice(GameDiceModel dice) {
		this.dice = dice;
	}
	
}
