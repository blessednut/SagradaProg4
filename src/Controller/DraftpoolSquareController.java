package Controller;

import model.GameDiceModel;

public class DraftpoolSquareController {
	private DraftpoolController2 controller;
	private GameDiceModel dice;
	
	public DraftpoolSquareController (DraftpoolController2 controller, GameDiceModel dice) {
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
			controller.setSelectedDice(dice);
		}
	}
	
	public GameDiceModel getDice () {
		return this.dice;
	}
}
