package Controller;

import View.DraftPoolSquareView;
import model.GameDiceModel;

public class DraftpoolSquareController {
	private DraftpoolController controller;
	private DraftPoolSquareView view;
	private GameDiceModel dice;
	
	public DraftpoolSquareController (DraftpoolController controller, GameDiceModel dice) {
		this.controller = controller;
		this.dice = dice;
	}
	
	public DraftpoolSquareController () {
		this.dice = null;
	}
	
	public void onClick () {
		System.out.println("Klik!");
		
		if (dice != null) {
			System.out.println("Dice color = " + dice.colorProperty().getValue() + " eyes = " + dice.valueProperty().getValue());
			controller.setSelectedDice(dice, this);
		}
	}
	
	public GameDiceModel getDice () {
		return this.dice;
	}
	
	public void removeDice () {
//		this.dice.colorProperty().setValue(null);
//		this.dice.valueProperty().setValue(null);
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
