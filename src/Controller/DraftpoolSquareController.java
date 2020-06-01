package Controller;

import View.DraftPoolSquareView;
import model.GameDiceModel;

public class DraftpoolSquareController {
	private GameController gameController;
	private DraftpoolController controller;
	private DraftPoolSquareView draftPoolView;
	private GameDiceModel diceModel;
	
	public DraftpoolSquareController (GameController gameController, DraftpoolController draftPoolController, GameDiceModel diceModel) {
		this.gameController = gameController;
		this.controller = draftPoolController;
		this.diceModel = diceModel;
	}
	
	public DraftpoolSquareController () {
		this.diceModel = null;
	}
	
	public void onClick () {
		if (gameController.getIsTurn()) {
			if (diceModel != null) {
				//System.out.println("Dice color = " + dice.colorProperty().getValue() + " eyes = " + dice.valueProperty().getValue());
				controller.setSelectedDice(diceModel, this);
			}
		}
	}
	
	public GameDiceModel getDice () {
		return this.diceModel;
	}
	
	public void removeDice () {
		this.diceModel = null;
		this.updateView();
	}
	
	public void setView (DraftPoolSquareView view) {
		this.draftPoolView = view;
	}
	
	public void updateView () {
		this.draftPoolView.updateView();
	}

	public void setDice(GameDiceModel dice) {
		this.diceModel = dice;
	}
	
}
