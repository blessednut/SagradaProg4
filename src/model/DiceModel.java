package model;

import java.util.ArrayList;

import Controller.GameController;
import DataBase.DiceDB;

public class DiceModel {
	private ArrayList<GameDiceModel> dice;
	private GameController gameController;
	private DiceDB con;
	
	public DiceModel (GameController gameController) {
		this.gameController = gameController;
		this.dice = new ArrayList<GameDiceModel>();
		this.con = new DiceDB();
		
		this.importGameDice(gameController.getM_game().getGameId());
	}
	
	public void addGameDice (GameDiceModel gameDice) {
		this.dice.add(gameDice);
	}
	
	public ArrayList<GameDiceModel> getBag () {
		return this.con.getBag(gameController.getM_game().getGameId());
	}
	
	public void addDice (GameDiceModel gameDice, int roundID) {
		this.dice.add(gameDice);
		
		//insert Query
		this.con.insertGameDice(gameDice, roundID);
	}
	
	private void importGameDice (int gameID) {
		this.dice = con.importGameDice(gameID);
	}
	
	//Maken update Query
}
